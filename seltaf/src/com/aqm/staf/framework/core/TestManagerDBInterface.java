package com.aqm.staf.framework.core;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.aqm.staf.framework.core.entity.Application;
import com.aqm.staf.framework.core.entity.ExecutionMasterTestSuites;
import com.aqm.staf.framework.core.entity.ExecutionTestScenarios;
import com.aqm.staf.framework.core.entity.ExecutionTestSteps;
import com.aqm.staf.framework.core.entity.ExecutionTestSuites;
import com.aqm.staf.framework.core.entity.MasterAutomationScript;
import com.aqm.staf.framework.core.entity.MasterAutomationScriptStep;
import com.aqm.staf.framework.core.entity.MasterTestSuite;
import com.aqm.staf.framework.core.entity.Module;
import com.aqm.staf.framework.core.entity.Project;
import com.aqm.staf.framework.core.entity.RuntimeConfigParameter;
import com.aqm.staf.framework.core.entity.TestPlan;
import com.aqm.staf.framework.core.entity.TestScenario;
import com.aqm.staf.framework.core.entity.TestSuite;

public class TestManagerDBInterface {
	private Session sessionTestManager;

	public TestManagerDBInterface (Session sessionTestManager) {
		this.sessionTestManager = sessionTestManager;
	}

	public void addUpdateProject (Project p) {
		Transaction t = sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(Project.class);
			criteria.add(Restrictions.eq("prj_Reference", p.getProjectReference()));

			Project recordExist = (Project) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getProjectReference().equalsIgnoreCase(p.getProjectReference())) {
				recordExist.setProjectDescription(p.getProjectDescription());
				recordExist.setProjectSerialNumber(p.getProjectSerialNumber());
				recordExist.setProjectDateCreated(p.getProjectDateCreated());
				recordExist.setProjectDateUpdated(p.getProjectDateUpdated());
				recordExist.setProjectDeleted(p.isProjectDeleted());
				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(p);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public Project getProjectByReference (String projectReferenceFilter) {
		Project project = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(Project.class);
			criteria.add(Restrictions.eq("prj_Reference", projectReferenceFilter));

			project = (Project) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return project;
	}

	public void addUpdateApplication (Application app) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(Application.class);
			criteria.add(Restrictions.eq("app_Reference", app.getApplicationReference()));

			Application recordExist = (Application) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getApplicationReference().equalsIgnoreCase(app.getApplicationReference())) {
				recordExist.setApplicationDescription(app.getApplicationDescription());
				recordExist.setApplicationProjectID(app.getApplicationProjectID());
				recordExist.setApplicationProjectReference(app.getApplicationProjectReference());
				recordExist.setApplicationSerialNumber(app.getApplicationSerialNumber());
				recordExist.setApplicationDateCreated(app.getApplicationDateCreated());
				recordExist.setApplicationDateUpdated(app.getApplicationDateUpdated());
				recordExist.setApplicationDeleted(app.isApplicationDeleted());

				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(app);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public Application getApplicationByReference (String applicationReferenceFilter) {
		Application application = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(Application.class);
			criteria.add(Restrictions.eq("app_Reference", applicationReferenceFilter));

			application = (Application) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return application;
	}

	public void addUpdateModule (Module mod) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(Module.class);
			criteria.add(Restrictions.eq("mod_Reference", mod.getModuleReference()));

			Module recordExist = (Module) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getModuleReference().equalsIgnoreCase(mod.getModuleReference())) {
				recordExist.setModuleDescription(mod.getModuleDescription());
				recordExist.setModuleSerialNumber(mod.getModuleSerialNumber());
				recordExist.setModuleProjectID(mod.getModuleProjectID());
				recordExist.setModuleProjectReference(mod.getModuleProjectReference());
				recordExist.setModuleApplicationID(mod.getModuleApplicationID());
				recordExist.setModuleApplicationReference(mod.getModuleApplicationReference());
				recordExist.setModuleDateCreated(mod.getModuleDateCreated());
				recordExist.setModuleDateUpdated(mod.getModuleDateUpdated());
				recordExist.setModuleDeleted(mod.isModuleDeleted());

				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(mod);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public Module getModuleByReference (String moduleReferenceFilter) {
		Module module = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(Module.class);
			criteria.add(Restrictions.eq("mod_Reference", moduleReferenceFilter));

			module = (Module) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return module;
	}

	public void addUpdateMasterAutomationScript (MasterAutomationScript mas) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(MasterAutomationScript.class);
			criteria.add(Restrictions.eq("masterAutomationScript_Reference", mas.getMasterAutomationScriptReference()));

			MasterAutomationScript recordExist = (MasterAutomationScript) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getMasterAutomationScriptReference().equalsIgnoreCase(mas.getMasterAutomationScriptReference())) {
				recordExist.setMasterAutomationScriptDescription(mas.getMasterAutomationScriptDescription());
				recordExist.setMasterAutomationScriptSerialNumber(mas.getMasterAutomationScriptSerialNumber());
				recordExist.setMasterAutomationScriptDateCreated(mas.getMasterAutomationScriptDateCreated());
				recordExist.setMasterAutomationScriptDateUpdated(mas.getMasterAutomationScriptDateUpdated());
				recordExist.setMasterAutomationScriptDeleted(mas.isMasterAutomationScriptDeleted());
				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(mas);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public MasterAutomationScript getMasterAutomationScriptByReference (String MasterAutomationScriptReferenceFilter) {
		MasterAutomationScript masterAutomationScript = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(MasterAutomationScript.class);
			criteria.add(Restrictions.eq("masterAutomationScript_Reference", MasterAutomationScriptReferenceFilter));

			masterAutomationScript = (MasterAutomationScript) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return masterAutomationScript;
	}

	public void addUpdateMasterAutomationScriptStep (MasterAutomationScriptStep mass) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(MasterAutomationScriptStep.class);
			criteria.add(Restrictions.eq("masterAutomationScriptStep_Reference", mass.getMasterAutomationScriptStepReference()));

			MasterAutomationScriptStep recordExist = (MasterAutomationScriptStep) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getMasterAutomationScriptStepReference().equalsIgnoreCase(mass.getMasterAutomationScriptStepReference())) {
				recordExist.setMasterAutomationScriptStepReference(mass.getMasterAutomationScriptStepReference());
				recordExist.setMasterAutomationScriptStepAutomationScriptID(mass.getMasterAutomationScriptStepAutomationScriptID());
				recordExist.setMasterAutomationScriptStepAutomationScriptReference(mass.getMasterAutomationScriptStepAutomationScriptReference());
				recordExist.setMasterAutomationScriptStepStepKeyword(mass.getMasterAutomationScriptStepStepKeyword());
				recordExist.setMasterAutomationScriptStepConfigData(mass.getMasterAutomationScriptStepConfigData());
				recordExist.setMasterAutomationScriptStepSerialNumber(mass.getMasterAutomationScriptStepSerialNumber());
				recordExist.setMasterAutomationScriptStepExecutionSequence(mass.getMasterAutomationScriptStepExecutionSequence());
				recordExist.setMasterAutomationScriptStepSkipStep(mass.getMasterAutomationScriptStepSkipStep());
				recordExist.setMasterAutomationScriptStepToBeExecutedByMachine(mass.getMasterAutomationScriptStepToBeExecutedByMachine());
				recordExist.setMasterAutomationScriptStepSerialNumber(mass.getMasterAutomationScriptStepSerialNumber());
				recordExist.setMasterAutomationScriptStepDateCreated(mass.getMasterAutomationScriptStepDateCreated());
				recordExist.setMasterAutomationScriptStepDateUpdated(mass.getMasterAutomationScriptStepDateUpdated());
				recordExist.setMasterAutomationScriptStepDeleted(mass.isMasterAutomationScriptStepDeleted());
				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(mass);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public MasterAutomationScriptStep getMasterAutomationScriptStepByReference (String MasterAutomationScriptStepReferenceFilter) {
		MasterAutomationScriptStep masterAutomationScriptStep = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(MasterAutomationScriptStep.class);
			criteria.add(Restrictions.eq("masterAutomationScriptStep_Reference", MasterAutomationScriptStepReferenceFilter));

			masterAutomationScriptStep = (MasterAutomationScriptStep) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return masterAutomationScriptStep;
	}
	
	public List<MasterAutomationScriptStep> getMasterAutomationScriptStepsByMasterAutomationScriptReference(String masterAutomationScriptReference) {
		List<MasterAutomationScriptStep> masterAutomationScriptSteps = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(MasterAutomationScriptStep.class);
			criteria.add(Restrictions.eq("masterAutomationScriptStep_AutomationScriptReference", masterAutomationScriptReference));
			criteria.addOrder(Order.asc("masterAutomationScriptStep_ExecutionSequence"));
			masterAutomationScriptSteps = criteria.list();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return masterAutomationScriptSteps;
	}

	public void addUpdateMasterTestSuite (MasterTestSuite mts) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(MasterTestSuite.class);
			criteria.add(Restrictions.eq("masterTestSuite_Reference", mts.getMasterTestSuiteReference()));

			MasterTestSuite recordExist = (MasterTestSuite) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getMasterTestSuiteReference().equalsIgnoreCase(mts.getMasterTestSuiteReference())) {
				recordExist.setMasterTestSuiteProjectID(mts.getMasterTestSuiteProjectID());
				recordExist.setMasterTestSuiteProjectReference(mts.getMasterTestSuiteProjectReference());
				recordExist.setMasterTestSuiteApplicationID(mts.getMasterTestSuiteApplicationID());
				recordExist.setMasterTestSuiteApplicationReference(mts.getMasterTestSuiteApplicationReference());
				recordExist.setMasterTestSuiteDescription(mts.getMasterTestSuiteDescription());
				recordExist.setMasterTestSuiteSerialNumber(mts.getMasterTestSuiteSerialNumber());
				recordExist.setMasterTestSuiteRepositoryFilePath(mts.getMasterTestSuiteRepositoryFilePath());
				recordExist.setMasterTestSuiteRepositoryAbsoluteFolderPath(mts.getMasterTestSuiteRepositoryAbsoluteFolderPath());
				recordExist.setMasterTestSuiteDateCreated(mts.getMasterTestSuiteDateCreated());
				recordExist.setMasterTestSuiteDateUpdated(mts.getMasterTestSuiteDateUpdated());
				recordExist.setMasterTestSuiteDeleted(mts.isMasterTestSuiteDeleted());

				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(mts);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public MasterTestSuite getMasterTestSuiteByReference (String masterTestSuiteReferenceFilter) {
		MasterTestSuite mts = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(MasterTestSuite.class);
			criteria.add(Restrictions.eq("masterTestSuite_Reference", masterTestSuiteReferenceFilter));

			mts = (MasterTestSuite) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return mts;
	}

	public void addUpdateTestSuite (TestSuite ts) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(TestSuite.class);
			criteria.add(Restrictions.eq("testSuite_Reference", ts.getTestSuiteReference()));

			TestSuite recordExist = (TestSuite) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getTestSuiteReference().equalsIgnoreCase(ts.getTestSuiteReference())) {

				recordExist.setTestSuiteProjectID(ts.getTestSuiteProjectID());
				recordExist.setTestSuiteProjectReference(ts.getTestSuiteProjectReference());
				recordExist.setTestSuiteApplicationID(ts.getTestSuiteApplicationID());
				recordExist.setTestSuiteApplicationReference(ts.getTestSuiteApplicationReference());
				recordExist.setTestSuiteModuleID(ts.getTestSuiteModuleID());
				recordExist.setTestSuiteModuleReference(ts.getTestSuiteModuleReference());
				recordExist.setTestSuiteMasterTestSuiteID(ts.getTestSuiteMasterTestSuiteID());
				recordExist.setTestSuiteMasterTestSuiteReference(ts.getTestSuiteMasterTestSuiteReference());
				recordExist.setTestSuiteDescription(ts.getTestSuiteDescription());
				recordExist.setTestSuiteRepositoryFile(ts.getTestSuiteRepositoryFile());
				recordExist.setTestSuiteRepositoryAbsoluteFolderPath(ts.getTestSuiteRepositoryAbsoluteFolderPath());
				recordExist.setTestSuiteExecutionPriority(ts.getTestSuiteExecutionPriority());
				//PENDING
				//recordExist.setTestSuiteConfigurationID(ts.setTestSuiteConfigurationID());
				//recordExist.setTestSuiteConfigurationReference(ts.setTestSuiteConfigurationReference());
				recordExist.setTestSuiteExecutionMode(ts.getTestSuiteExecutionMode());
				recordExist.setTestSuiteSerialNumber(ts.getTestSuiteSerialNumber());
				recordExist.setTestSuiteDateCreated(ts.getTestSuiteDateCreated());
				recordExist.setTestSuiteDateUpdated(ts.getTestSuiteDateUpdated());
				recordExist.setTestSuiteDeleted(ts.isTestSuiteDeleted());

				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(ts);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public TestSuite getTestSuiteByReference (String testSuiteReferenceFilter) {
		TestSuite ts = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(TestSuite.class);
			criteria.add(Restrictions.eq("testSuite_Reference", testSuiteReferenceFilter));

			ts = (TestSuite) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return ts;
	}

	public void addUpdateTestScenario (TestScenario tsc) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(TestScenario.class);
			criteria.add(Restrictions.eq("testScenario_Reference", tsc.getTestScenarioReference()));

			TestScenario recordExist = (TestScenario) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getTestScenarioReference().equalsIgnoreCase(tsc.getTestScenarioReference())) {
				recordExist.setTestScenarioReference(tsc.getTestScenarioReference());
				recordExist.setTestScenarioProjectID(tsc.getTestScenarioProjectID());
				recordExist.setTestScenarioProjectReference(tsc.getTestScenarioProjectReference());
				recordExist.setTestScenarioApplicationID(tsc.getTestScenarioApplicationID());
				recordExist.setTestScenarioApplicationReference(tsc.getTestScenarioApplicationReference());
				recordExist.setTestScenarioMasterTestSuiteID(tsc.getTestScenarioMasterTestSuiteID());
				recordExist.setTestScenarioMasterTestSuiteReference(tsc.getTestScenarioMasterTestSuiteReference());
				recordExist.setTestScenarioTestSuiteID(tsc.getTestScenarioTestSuiteID());
				recordExist.setTestScenarioTestSuiteReference(tsc.getTestScenarioTestSuiteReference());
				recordExist.setTestScenarioPrerequisiteTestSuiteID(tsc.getTestScenarioPrerequisiteTestSuiteID());
				recordExist.setTestScenarioPrerequisiteTestSuiteReference(tsc.getTestScenarioPrerequisiteTestSuiteReference());
				recordExist.setTestScenarioPrerequisiteTestScenarioID(tsc.getTestScenarioPrerequisiteTestScenarioID());
				recordExist.setTestScenarioPrerequisiteTestScenarioReference(tsc.getTestScenarioPrerequisiteTestScenarioReference());
				recordExist.setTestScenarioDescription(tsc.getTestScenarioDescription());
				recordExist.setTestScenarioMasterAutomationScriptID(tsc.getTestScenarioMasterAutomationScriptID());
				recordExist.setTestScenarioMasterAutomationScriptReference(tsc.getTestScenarioMasterAutomationScriptReference());
				recordExist.setTestScenarioSerialNumber(tsc.getTestScenarioSerialNumber());

				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(tsc);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public TestScenario getTestScenarioByReference (String testScenarioReferenceFilter) {
		TestScenario tsc = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(TestScenario.class);
			criteria.add(Restrictions.eq("testScenario_Reference", testScenarioReferenceFilter));

			tsc = (TestScenario) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return tsc;
	}

	public RuntimeConfigParameter getRuntimeConfigParameterByReference(String runtimeConfigParametersFilter) {
		RuntimeConfigParameter runtimeConfigParameter = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(RuntimeConfigParameter.class);
			criteria.add(Restrictions.eq("runtimeConfigParameters_Reference", runtimeConfigParametersFilter));

			runtimeConfigParameter = (RuntimeConfigParameter) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return runtimeConfigParameter;
	}

	public void addUpdateRuntimeConfigParameter (RuntimeConfigParameter runtimeConfigParameter) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(RuntimeConfigParameter.class);
			criteria.add(Restrictions.eq("runtimeConfigParameters_Reference", runtimeConfigParameter.getRuntimeConfigParametersReference()));

			RuntimeConfigParameter recordExist = (RuntimeConfigParameter) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getRuntimeConfigParametersReference().equalsIgnoreCase(runtimeConfigParameter.getRuntimeConfigParametersReference())) {
				recordExist.setRuntimeConfigParametersKeyData(runtimeConfigParameter.getRuntimeConfigParametersKeyData());
				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(runtimeConfigParameter);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public void addUpdateTestPlan (TestPlan testPlan) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(TestPlan.class);
			criteria.add(Restrictions.eq("testPlan_Reference", testPlan.getTestPlanReference()));

			TestPlan recordExist = (TestPlan) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getTestPlanReference().equalsIgnoreCase(testPlan.getTestPlanReference())) {
				recordExist.setTestPlanDescription(testPlan.getTestPlanDescription());
				recordExist.setTestPlanDateCreated(testPlan.getTestPlanDateCreated());
				recordExist.setTestPlanDateUpdated(testPlan.getTestPlanDateUpdated());
				recordExist.setTestPlanDeleted(testPlan.isTestPlanDeleted());

				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(testPlan);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public TestPlan getTestPlanByReference(String testPlanFilter) {
		TestPlan testPlan = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(TestPlan.class);
			criteria.add(Restrictions.eq("testPlan_Reference", testPlanFilter));

			testPlan = (TestPlan) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return testPlan;
	}

	public void addUpdateExecutionMasterTestSuite(ExecutionMasterTestSuites executionMasterTestSuite) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionMasterTestSuites.class);
			criteria.add(Restrictions.eq("executionMasterTestSuite_Reference", executionMasterTestSuite.getExecutionMasterTestSuiteReference()));

			ExecutionMasterTestSuites recordExist = (ExecutionMasterTestSuites) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getExecutionMasterTestSuiteReference().equalsIgnoreCase(executionMasterTestSuite.getExecutionMasterTestSuiteReference())) {
				recordExist.setExecutionMasterTestSuiteDescription(executionMasterTestSuite.getExecutionMasterTestSuiteDescription());
				recordExist.setExecutionMasterTestSuiteTestPlanID(executionMasterTestSuite.getExecutionMasterTestSuiteTestPlanID());
				recordExist.setExecutionMasterTestSuiteTestPlanReference(executionMasterTestSuite.getExecutionMasterTestSuiteTestPlanReference());
				recordExist.setExecutionMasterTestSuiteMasterTestSuiteID(executionMasterTestSuite.getExecutionMasterTestSuiteMasterTestSuiteID());
				recordExist.setExecutionMasterTestSuiteMasterTestSuiteReference(executionMasterTestSuite.getExecutionMasterTestSuiteTestPlanReference());
				recordExist.setExecutionMasterTestSuiteSerialNumber(executionMasterTestSuite.getExecutionMasterTestSuiteSerialNumber());
				recordExist.setExecutionMasterTestSuiteExecutionStatus(executionMasterTestSuite.getExecutionMasterTestSuiteExecutionStatus());
				recordExist.setExecutionMasterTestSuiteDateCreated(executionMasterTestSuite.getExecutionMasterTestSuiteDateCreated());
				recordExist.setExecutionMasterTestSuiteDateUpdated(executionMasterTestSuite.getExecutionMasterTestSuiteDateUpdated());
				recordExist.setExecutionMasterTestSuiteDeleted(executionMasterTestSuite.isExecutionMasterTestSuiteDeleted());

				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(executionMasterTestSuite);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public ExecutionMasterTestSuites getExecutionMasterTestSuiteByReference(String executionMasterTestSuiteFilter) {
		ExecutionMasterTestSuites executionMasterTestSuite = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionMasterTestSuites.class);
			criteria.add(Restrictions.eq("executionMasterTestSuite_Reference", executionMasterTestSuiteFilter));

			executionMasterTestSuite = (ExecutionMasterTestSuites) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return executionMasterTestSuite;
	}

	public ExecutionMasterTestSuites getExecutionMasterTestSuiteByCriteria (Properties executionMasterTestSuiteFilterProperties) {
		ExecutionMasterTestSuites executionMasterTestSuite = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionMasterTestSuites.class);
			Enumeration<Object> filterColumns = executionMasterTestSuiteFilterProperties.keys();
			while(filterColumns.hasMoreElements()) {
				String columnName = (String) filterColumns.nextElement();
				String columnValue = executionMasterTestSuiteFilterProperties.getProperty(columnName);
				criteria.add(Restrictions.eq(columnName, columnValue));
			}

			executionMasterTestSuite = (ExecutionMasterTestSuites) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return executionMasterTestSuite;
	}

	public List<ExecutionMasterTestSuites> getAllExecutionMasterTestSuiteByTestPlanReference(String executionTestPlanReferenceFilter) {
		List<ExecutionMasterTestSuites> allExecutionMasterTestSuite = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionMasterTestSuites.class);
			criteria.add(Restrictions.eq("executionMasterTestSuite_TestPlanReference", executionTestPlanReferenceFilter));
			allExecutionMasterTestSuite = criteria.list();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return allExecutionMasterTestSuite;
	}

	public void addUpdateExecutionTestSuite(ExecutionTestSuites executionTestSuite) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionTestSuites.class);
			criteria.add(Restrictions.eq("executionTestSuite_Reference", executionTestSuite.getExecutionTestSuiteReference()));

			ExecutionTestSuites recordExist = (ExecutionTestSuites) criteria.uniqueResult();
			if (recordExist!=null && recordExist.getExecutionTestSuiteReference().equalsIgnoreCase(executionTestSuite.getExecutionTestSuiteReference())) {
				recordExist.setExecutionTestSuiteDescription(executionTestSuite.getExecutionTestSuiteDescription());
				recordExist.setExecutionTestSuiteExecutionMasterTestSuiteID(executionTestSuite.getExecutionTestSuiteExecutionMasterTestSuiteID());
				recordExist.setExecutionTestSuiteExecutionMasterTestSuiteReference(executionTestSuite.getExecutionTestSuiteExecutionMasterTestSuiteReference());
				recordExist.setExecutionTestSuiteTestPlanID(executionTestSuite.getExecutionTestSuiteTestPlanID());
				recordExist.setExecutionTestSuiteTestPlanReference(executionTestSuite.getExecutionTestSuiteTestPlanReference());
				recordExist.setExecutionTestSuiteMasterTestSuiteID(executionTestSuite.getExecutionTestSuiteMasterTestSuiteID());
				recordExist.setExecutionTestSuiteMasterTestSuiteReference(executionTestSuite.getExecutionTestSuiteMasterTestSuiteReference());
				recordExist.setExecutionTestSuiteTestSuiteID(executionTestSuite.getExecutionTestSuiteTestSuiteID());
				recordExist.setExecutionTestSuiteTestSuiteReference(executionTestSuite.getExecutionTestSuiteTestSuiteReference());
				recordExist.setExecutionTestSuiteTestSuiteRepositoryFile(executionTestSuite.getExecutionTestSuiteTestSuiteRepositoryFile());
				recordExist.setExecutionTestSuiteTestSuiteExecutionPriority(executionTestSuite.getExecutionTestSuiteTestSuiteExecutionPriority());
				recordExist.setExecutionTestSuiteTestSuiteExecutionMode(executionTestSuite.getExecutionTestSuiteTestSuiteExecutionMode());
				recordExist.setExecutionTestSuiteTestSuiteSerialNumber(executionTestSuite.getExecutionTestSuiteTestSuiteSerialNumber());
				recordExist.setExecutionTestSuiteExecutionStatus(executionTestSuite.getExecutionTestSuiteExecutionStatus());
				recordExist.setExecutionTestSuiteDeleted(recordExist.isExecutionTestSuiteDeleted());

				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(executionTestSuite);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public ExecutionTestSuites getExecutionTestSuiteByReference(String executionTestSuiteFilter) {
		ExecutionTestSuites executionTestSuite = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionTestSuites.class);
			criteria.add(Restrictions.eq("executionTestSuite_Reference", executionTestSuiteFilter));

			executionTestSuite = (ExecutionTestSuites) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return executionTestSuite;
	}	

	public List<ExecutionTestSuites> getAllExecutionTestSuiteByExecutionMasterTestSuiteReference(String executionMasterTestSuiteReferenceFilter) {
		List<ExecutionTestSuites> allMasterTestSuite = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionTestSuites.class);
			criteria.add(Restrictions.eq("executionTestSuite_ExecutionMasterTestSuiteReference", executionMasterTestSuiteReferenceFilter));
			allMasterTestSuite = criteria.list();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return allMasterTestSuite;
	}
	
	public ExecutionTestSuites getExecutionTestSuiteByCriteria (Properties executionTestSuiteFilterProperties) {
		ExecutionTestSuites executionTestSuite = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionTestSuites.class);
			Enumeration<Object> filterColumns = executionTestSuiteFilterProperties.keys();
			while(filterColumns.hasMoreElements()) {
				String columnName = (String) filterColumns.nextElement();
				String columnValue = executionTestSuiteFilterProperties.getProperty(columnName);
				criteria.add(Restrictions.eq(columnName, columnValue));
			}

			executionTestSuite = (ExecutionTestSuites) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return executionTestSuite;
	}
	
	public void addUpdateExecutionTestScenario (ExecutionTestScenarios executionTestScenario) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionTestScenarios.class);
			criteria.add(Restrictions.eq("executionTestScenario_Reference", executionTestScenario.getExecutionTestScenarioReference()));

			ExecutionTestScenarios recordExist = (ExecutionTestScenarios) criteria.uniqueResult();

			if (recordExist!=null && recordExist.getExecutionTestScenarioReference().equalsIgnoreCase(executionTestScenario.getExecutionTestScenarioReference())) {
				recordExist.setExecutionTestScenarioDescription(executionTestScenario.getExecutionTestScenarioDescription());
				recordExist.setExecutionTestScenarioExecutionTestSuiteID(executionTestScenario.getExecutionTestScenarioExecutionTestSuiteID());
				recordExist.setExecutionTestScenarioExecutionTestSuiteReference(executionTestScenario.getExecutionTestScenarioExecutionTestSuiteReference());
				recordExist.setExecutionTestScenarioTestPlanID(executionTestScenario.getExecutionTestScenarioTestPlanID());
				recordExist.setExecutionTestScenarioTestPlanReference(executionTestScenario.getExecutionTestScenarioTestPlanReference());
				recordExist.setExecutionTestScenarioMasterTestSuiteID(executionTestScenario.getExecutionTestScenarioMasterTestSuiteID());
				recordExist.setExecutionTestScenarioMasterTestSuiteReference(executionTestScenario.getExecutionTestScenarioMasterTestSuiteReference());
				recordExist.setExecutionTestScenarioTestSuiteID(executionTestScenario.getExecutionTestScenarioTestSuiteID());
				recordExist.setExecutionTestScenarioTestSuiteReference(executionTestScenario.getExecutionTestScenarioTestSuiteReference());
				recordExist.setExecutionTestScenarioTestScenarioID(executionTestScenario.getExecutionTestScenarioTestScenarioID());
				recordExist.setExecutionTestScenarioTestScenarioReference(executionTestScenario.getExecutionTestScenarioTestScenarioReference());
				recordExist.setExecutionTestScenarioSerialNumber(executionTestScenario.getExecutionTestScenarioSerialNumber());
				recordExist.setExecutionTestScenarioExecutionStatus(executionTestScenario.getExecutionTestScenarioExecutionStatus());
				recordExist.setExecutionTestScenarioLogFile(executionTestScenario.getExecutionTestScenarioLogFile());
				recordExist.setExecutionTestScenarioSnapShotFolder(executionTestScenario.getExecutionTestScenarioSnapShotFolder());
				recordExist.setExecutionTestScenarioNextSnapShotNumber(executionTestScenario.getExecutionTestScenarioNextSnapShotNumber());
				recordExist.setExecutionTestScenarioErrorMessage(executionTestScenario.getExecutionTestScenarioErrorMessage());
				recordExist.setExecutionTestScenarioNextExecutionAutomationScriptStepToBeExecutedByMachine(executionTestScenario.getExecutionTestScenarioNextExecutionAutomationScriptStepToBeExecutedByMachine());
				recordExist.setExecutionTestScenarioNextExecutionAutomationScriptID(executionTestScenario.getExecutionTestScenarioNextExecutionAutomationScriptID());
				recordExist.setExecutionTestScenarioNextExecutionAutomationScriptReference(executionTestScenario.getExecutionTestScenarioNextExecutionAutomationScriptReference());
				recordExist.setExecutionTestScenarioNextExecutionAutomationScriptStepID(executionTestScenario.getExecutionTestScenarioNextExecutionAutomationScriptStepID());
				recordExist.setExecutionTestScenarioNextExecutionAutomationScriptStepReference(executionTestScenario.getExecutionTestScenarioNextExecutionAutomationScriptStepReference());
				recordExist.setExecutionTestScenarioNextExecutionAutomationScriptStepExecutionSequence(executionTestScenario.getExecutionTestScenarioNextExecutionAutomationScriptStepExecutionSequence());
				recordExist.setExecutionTestScenarioNextExecutionBusinessDate(executionTestScenario.getExecutionTestScenarioNextExecutionBusinessDate());
				recordExist.setExecutionTestScenarioStartTime(executionTestScenario.getExecutionTestScenarioStartTime());
				recordExist.setExecutionTestScenarioEndTime(executionTestScenario.getExecutionTestScenarioEndTime());
				recordExist.setExecutionTestScenarioDateCreated(executionTestScenario.getExecutionTestScenarioDateCreated());
				recordExist.setExecutionTestScenarioDateUpdated(executionTestScenario.getExecutionTestScenarioDateUpdated());
				recordExist.setExecutionTestScenarioDeleted(executionTestScenario.isExecutionTestScenarioDeleted());

				sessionTestManager.update(recordExist);
			}
			else
				sessionTestManager.persist(executionTestScenario);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public ExecutionTestScenarios getExecutionTestScenarioByReference(String executionTestScenarioFilter) {
		ExecutionTestScenarios executionTestScenario = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionTestScenarios.class);
			criteria.add(Restrictions.eq("executionTestScenario_Reference", executionTestScenarioFilter));

			executionTestScenario = (ExecutionTestScenarios) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return executionTestScenario;
	}
	
	public List<ExecutionTestScenarios> getAllExecutionTestScenariosByExecutionTestSuiteReference(String executionTestSuiteReferenceFilter) {
		List<ExecutionTestScenarios> allMasterTestScenarios = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionTestScenarios.class);
			criteria.add(Restrictions.eq("executionTestScenario_ExecutionTestSuiteReference", executionTestSuiteReferenceFilter));
			allMasterTestScenarios = criteria.list();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return allMasterTestScenarios;
	}
	
	public ExecutionTestScenarios getExecutionTestScenarioByCriteria (Properties executionTestScenarioFilterProperties) {
		ExecutionTestScenarios executionTestScenario = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionTestScenarios.class);
			Enumeration<Object> filterColumns = executionTestScenarioFilterProperties.keys();
			while(filterColumns.hasMoreElements()) {
				String columnName = (String) filterColumns.nextElement();
				String columnValue = executionTestScenarioFilterProperties.getProperty(columnName);
				criteria.add(Restrictions.eq(columnName, columnValue));
			}

			executionTestScenario = (ExecutionTestScenarios) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return executionTestScenario;
	}

	public void addUpdateExecutionTestStep (ExecutionTestSteps executionTestStep) {
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionTestSteps.class);
			criteria.add(Restrictions.eq("executionTestStep_Reference", executionTestStep.getExecutionTestStepReference()));

			ExecutionTestSteps recordExist = (ExecutionTestSteps) criteria.uniqueResult();

			if (recordExist!=null && recordExist.getExecutionTestStepReference().equalsIgnoreCase(executionTestStep.getExecutionTestStepReference())) {
				recordExist.setExecutionTestStepDescription(executionTestStep.getExecutionTestStepDescription());
				recordExist.setExecutionTestStepExecutionTestScenarioID(executionTestStep.getExecutionTestStepExecutionTestScenarioID());
				recordExist.setExecutionTestStepExecutionTestScenarioReference(executionTestStep.getExecutionTestStepExecutionTestScenarioReference());
				recordExist.setExecutionTestStepTestPlanID(executionTestStep.getExecutionTestStepTestPlanID());
				recordExist.setExecutionTestStepTestPlanReference(executionTestStep.getExecutionTestStepTestPlanReference());
				recordExist.setExecutionTestStepMasterTestSuiteID(executionTestStep.getExecutionTestStepMasterTestSuiteID());
				recordExist.setExecutionTestStepMasterTestSuiteReference(executionTestStep.getExecutionTestStepMasterTestSuiteReference());
				recordExist.setExecutionTestStepTestSuiteID(executionTestStep.getExecutionTestStepTestSuiteID());
				recordExist.setExecutionTestStepTestSuiteReference(executionTestStep.getExecutionTestStepTestSuiteReference());
				recordExist.setExecutionTestStepTestScenarioID(executionTestStep.getExecutionTestStepTestScenarioID());
				recordExist.setExecutionTestStepTestScenarioReference(executionTestStep.getExecutionTestStepTestScenarioReference());
				recordExist.setExecutionTestStepMasterAutomationScriptID(executionTestStep.getExecutionTestStepMasterAutomationScriptID());
				recordExist.setExecutionTestStepMasterAutomationScriptReference(executionTestStep.getExecutionTestStepMasterAutomationScriptReference());
				recordExist.setExecutionTestStepMasterAutomationScriptStepID(executionTestStep.getExecutionTestStepMasterAutomationScriptStepID());
				recordExist.setExecutionTestStepMasterAutomationScriptStepReference(executionTestStep.getExecutionTestStepMasterAutomationScriptStepReference());
				recordExist.setExecutionTestStepMasterAutomationScriptStepKeyword(executionTestStep.getExecutionTestStepMasterAutomationScriptStepKeyword());
				recordExist.setExecutionTestStepMasterAutomationScriptStepConfigData(executionTestStep.getExecutionTestStepMasterAutomationScriptStepConfigData());
				recordExist.setExecutionTestStepMasterAutomationScriptStepExecutionSequence(executionTestStep.getExecutionTestStepMasterAutomationScriptStepExecutionSequence());
				recordExist.setExecutionTestStepMasterAutomationScriptStepToBeExecutedOnMachine(executionTestStep.getExecutionTestStepMasterAutomationScriptStepToBeExecutedOnMachine());
				recordExist.setExecutionTestStepExecutionStatus(executionTestStep.getExecutionTestStepExecutionStatus());
				recordExist.setExecutionTestStepDeleted(executionTestStep.isExecutionTestStepDeleted());
				sessionTestManager.update(executionTestStep);
			}
			else
				sessionTestManager.persist(executionTestStep);

			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
	}

	public ExecutionTestSteps getExecutionTestStepByReference(String executionTestStepFilter) {
		ExecutionTestSteps executionTestStep = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionTestSteps.class);
			criteria.add(Restrictions.eq("executionTestStep_Reference", executionTestStepFilter));

			executionTestStep = (ExecutionTestSteps) criteria.uniqueResult();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return executionTestStep;
	}
	
	public List<ExecutionTestSteps> getAllExecutionTestStepsByExecutionTestScenarioReference(String executionTestScenarioReferenceFilter) {
		List<ExecutionTestSteps> allTestSteps = null;
		Transaction t=sessionTestManager.beginTransaction();
		try {
			Criteria criteria = sessionTestManager.createCriteria(ExecutionTestSteps.class);
			criteria.add(Restrictions.eq("executionTestStep_ExecutionTestScenarioReference", executionTestScenarioReferenceFilter));
			criteria.addOrder(Order.asc("executionTestStep_MasterAutomationScriptStepExecutionSequence"));
			allTestSteps = criteria.list();
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		return allTestSteps;
	}
}
