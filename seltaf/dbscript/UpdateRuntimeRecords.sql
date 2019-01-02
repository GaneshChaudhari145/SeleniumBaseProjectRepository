USE [NIATestAutomationScenariosDB];
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