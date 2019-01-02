package com.aqm.staf.framework.core.scenarioResult;

public class WriteScenarioResultInExcel {
	
	public WriteScenarioResultInExcel(String workbookName,String filepath,StringBuilder[][] table){
		String headerLine[]={"Serial Number","Platform Name","Browser Name-Version","Scenario ID","Date Time Of Execution","Duration Of Execution","Scenario Status"};
		WorkbookBuilder generateExcelResult = new WorkbookBuilder();
		generateExcelResult.mkXlsx(workbookName, filepath, headerLine, table);
	}


	

	
}
