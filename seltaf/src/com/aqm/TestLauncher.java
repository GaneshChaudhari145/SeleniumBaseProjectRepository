package com.aqm;
import java.io.File;
import java.io.IOException;

import com.aqm.staf.framework.core.MapOfUserIDAssignedToAllocation;
import com.aqm.staf.framework.core.Uploader;
import com.aqm.staf.framework.core.scenarioResult.ScenarioResultCollection;
import com.aqm.staf.framework.core.scenarioResult.ScenarioResultStringGen;
import com.aqm.staf.framework.core.scenarioResult.WriteScenarioResultInCSV;
import com.aqm.staf.framework.core.scenarioResult.WriteScenarioResultInExcel;

public class TestLauncher {
		
	public static void main (String[] args) throws Exception {
		try {
			MapOfUserIDAssignedToAllocation.getInstance();
			Uploader uploader = new Uploader();
			uploader.uploadDataFromExcelToDatabase();
			uploader.createExecutionRecords();
			uploader.launchExecution();
			uploader.updateExecutionLog();
			uploader.closeDBSessions();
			new WriteScenarioResultInExcel("ResultSummary",uploader.getCurrentExecutionResultsFolder(),ScenarioResultStringGen.ResultSetInStringBuilder(ScenarioResultCollection.getListOfAllScenarioResult()));
		
			System.out.println("Execution Completed.");
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
}
}

