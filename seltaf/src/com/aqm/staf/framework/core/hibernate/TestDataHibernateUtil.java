package com.aqm.staf.framework.core.hibernate;

import java.io.File;
import java.util.Properties;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Mappings;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@SuppressWarnings("deprecation")
public class TestDataHibernateUtil {
	private static TestDataHibernateUtil instance;
	private Configuration configuration;
	private SessionFactory sessionFactory;
	private Session session;
	private ServiceRegistry serviceRegistry;
	private Mappings mappings;

	public synchronized static TestDataHibernateUtil getInstance() {
		if (instance == null) {
			instance = new TestDataHibernateUtil();
		}
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	private synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration tmp = getConfiguration();
			Properties prop = tmp.getProperties();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(prop).build();
			try {
				sessionFactory = tmp.buildSessionFactory(serviceRegistry);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			//serviceRegistry = new ServiceRegistryBuilder().applySettings(getConfiguration().getProperties()).build();
			//sessionFactory = getConfiguration().buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}
	
	public synchronized Session getCurrentSession() {
		if (session == null) {
			session = getSessionFactory().openSession();
			session.setFlushMode(FlushMode.COMMIT);
		}
		return session;
	}
	
	public synchronized Session getNewSession() {
		Session tempSession = null;
		tempSession = getSessionFactory().openSession();
		tempSession.setFlushMode(FlushMode.COMMIT);
		return tempSession;
	}
	
	public synchronized void closeSession(Session tempSession) {
		if (tempSession != null) {
			tempSession.flush();
			if (tempSession.isOpen()) {
				tempSession.close();
			}
		}
	}

	public synchronized Configuration getConfiguration() {
		if (configuration == null) {
			System.out.print("configuring Hibernate ... ");
			try {
				configuration = new Configuration().configure("hibernateTestData.cfg.xml");
				//mappings = configuration.createMappings();
			} catch (HibernateException e) {
				System.out.println("failure");
				e.printStackTrace();
			}

			/*try {
				configuration = new Configuration();
				configuration.addResource("hibernateTestData.cfg.xml");

				//configuration.addClass(Contact.class);

				configuration.configure("hibernateTestData.cfg.xml");
				//SchemaExport schemaExport = new SchemaExport(configuration);
				//schemaExport.create(true,true);
				
				//mappings = configuration.buildMapping();
				mappings = configuration.createMappings();
				System.out.println("ok");
			} catch (HibernateException e) {
				System.out.println("failure");
				e.printStackTrace();
			}*/
		}
		return configuration;
	}
	
	public void reset() {
		Session session = getCurrentSession();
		if (session != null) {
			session.flush();
			if (session.isOpen()) {
				System.out.print("closing session ... ");
				session.close();
				System.out.println("ok");
			}
		}
		SessionFactory sf = getSessionFactory();
		if (sf != null) {
			System.out.print("closing session factory ... ");
			sf.close();
			System.out.println("ok");
		}
		this.configuration = null;
		this.sessionFactory = null;
		this.session = null;
	}

	public PersistentClass getClassMapping(Class entityClass){
		return getConfiguration().getClassMapping(entityClass.getName());
	}
	
	public Mappings getCurrentMappings(){
		return mappings;
	}

	public void attachHBMS(File hbmFolderPath) {
		getConfiguration().addDirectory(hbmFolderPath);
		mappings = getConfiguration().createMappings();
	}

	public void addUpdatedTableToDatabase(String hbmFile) {
		getConfiguration().addFile(hbmFile);

		/*configuration.configure("hibernateTestData.cfg.xml");
		SchemaExport schemaExport = new SchemaExport(configuration);
		schemaExport.create(true,true);*/
		
		mappings = getConfiguration().createMappings();
	}
	
	public void exportSchema() {
		SchemaExport schemaExport = new SchemaExport(getConfiguration());
		schemaExport.create(true,true);
		
		mappings = getConfiguration().createMappings();
	}
	
	
}