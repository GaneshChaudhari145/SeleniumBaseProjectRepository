USE [NIATestAutomationScenariosDB];
Delete from app_TestPlan;
Delete from app_ExecutionMasterTestSuites;
Delete from app_ExecutionTestSuites;
Delete from app_ExecutionTestScenarios;
Delete from app_ExecutionTestSteps;

Delete from app_Projects;
Delete from app_Applications;
Delete from app_Modules;
Delete from app_MasterAutomationScripts;
Delete from app_MasterAutomationScriptSteps;
Delete from app_MasterTestSuites;
Delete from app_TestSuites;
Delete from app_TestScenarios;

UPDATE [dbo].[app_RuntimeConfigParameters]
   SET [runtimeConfigParameters_KeyData] = '1'
 WHERE [runtimeConfigParameters_Reference] = 'TestPlanNextID';

UPDATE [dbo].[app_RuntimeConfigParameters]
   SET [runtimeConfigParameters_KeyData] = '1'
 WHERE [runtimeConfigParameters_Reference] = 'ExecutionMasterTestSuiteNextID';

UPDATE [dbo].[app_RuntimeConfigParameters]
   SET [runtimeConfigParameters_KeyData] = '1'
 WHERE [runtimeConfigParameters_Reference] = 'ExecutionTestSuiteNextID';

UPDATE [dbo].[app_RuntimeConfigParameters]
   SET [runtimeConfigParameters_KeyData] = '1'
 WHERE [runtimeConfigParameters_Reference] = 'ExecutionTestScenarioNextID';

UPDATE [dbo].[app_RuntimeConfigParameters]
   SET [runtimeConfigParameters_KeyData] = '1'
 WHERE [runtimeConfigParameters_Reference] = 'ExecutionTestStepNextID';
