package com.aqm.staf.framework.core;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.aqm.staf.library.databin.GenericEntity;
import com.aqm.staf.library.databin.LoginEntity;
import com.aqm.staf.library.databin.UserEntity;

public class ScenarioTestData {
	Session sessionTestData;
	List<LoginEntity> loginrecords;
	List<UserEntity> userRecords;
	public ScenarioTestData (Session sessionTestData, String testScenarioReference) {
		this.sessionTestData = sessionTestData;
		loginrecords=getLoginRecordsForScenario(testScenarioReference);
		userRecords=getUserRecordsForScenario(testScenarioReference);
	}
	private List<LoginEntity> getLoginRecordsForScenario(String testScenarioReference) {
		List<LoginEntity> loginEntityList = getAllRecordsForCriteria(LoginEntity.class, testScenarioReference);
		return loginEntityList;
	}
	public void setLoginEntityRecords(
			List<LoginEntity> loginEntityRecords) {
		this.loginrecords = loginEntityRecords;
	}
	public List<LoginEntity> getLoginEntityRecords() {
		return loginrecords;
	}


	private List<UserEntity> getUserRecordsForScenario(String testScenarioReference) {
		List<UserEntity> userEntityList = getAllRecordsForCriteria(UserEntity.class, testScenarioReference);
		return userEntityList;
	}
	public void setuserEntityRecords(
			List<UserEntity> userEntityRecords) {
		this.userRecords = userEntityRecords;
	}
	public List<UserEntity> getUserEntityRecords() {
		return userRecords;
	}
	public <T> Object getRecordForCriteria (Class classObject, String testScenarioReference) {
		Object returnObject = null;
		Transaction t=sessionTestData.beginTransaction();
		try {
			Criteria criteria = sessionTestData.createCriteria(classObject);
			criteria.add(Restrictions.eq("Reference", testScenarioReference));
			returnObject = criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		return returnObject;
	}
	public <T> List<T> getAllRecordsForCriteria (Class classObject, String testScenarioReference) {
		List<T> returnObject = null;
		Transaction t=sessionTestData.beginTransaction();
		try {
			Criteria criteria = sessionTestData.createCriteria(classObject);
			criteria.add(Restrictions.eq("TestScenario", testScenarioReference));
			criteria.addOrder(Order.asc("RowNumber"));
			returnObject = criteria.list();
			t.commit();

		}
		catch(Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		return returnObject;
	}

	public static <T> ArrayList<T> getObjectByReference(List<T> list,String keys){
		ArrayList<T> filteredDataList=new ArrayList<T>();
		if (keys.equalsIgnoreCase("")){
			return filteredDataList;
		}
		String[] key=keys.split(",");
		for(int i=0;i<key.length;i++){
			for(T t:list){
				if(((GenericEntity)t).getReference().trim().equalsIgnoreCase(key[i].trim())){
					filteredDataList.add(t);
				}
			}
		}
		return filteredDataList;
	}

	public void updateRecordsForCriteria(Object object){
		Transaction t=sessionTestData.beginTransaction();
		try {
			sessionTestData.update(object);
			sessionTestData.persist(object);
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
			e.printStackTrace();
			System.out.println("Entity Updation Error => "+e.getClass().getSimpleName());
		}
	}
}
