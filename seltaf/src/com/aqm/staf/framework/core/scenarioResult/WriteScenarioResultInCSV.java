package com.aqm.staf.framework.core.scenarioResult;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteScenarioResultInCSV {

	public static Boolean writeIntoFile(File FileName,ArrayList<ArrayList<String>> allLinesOfData) {
		try {
			String headerLine="Serial Number,Platform Name,Browser Name-Version,Lob,Product,Scenario ID,Date Time Of Execution,Duration Of Execution,Scenario Status,Reason If Failed,Type Of Failure,Scripter Name";
			java.io.FileWriter fw = new FileWriter(FileName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(headerLine);
			bw.newLine();
			for (ArrayList<String> oneLine : allLinesOfData) {
				for(String aWord: oneLine){
					aWord=aWord+",";
					bw.write(aWord);	
				}
				bw.newLine();
			}
			
			bw.flush();
			bw.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
