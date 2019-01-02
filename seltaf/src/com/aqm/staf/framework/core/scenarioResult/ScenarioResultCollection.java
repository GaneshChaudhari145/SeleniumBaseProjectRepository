package com.aqm.staf.framework.core.scenarioResult;

import java.util.ArrayList;

public class ScenarioResultCollection {
	
	public static ScenarioResultCollection instance=null;
	public static ArrayList<ScenarioResultObject> listOfAllScenarioResult;
	
			
			public static ArrayList<ScenarioResultObject> getListOfAllScenarioResult() {
					return listOfAllScenarioResult;
					}

			public ScenarioResultCollection(){
			if(listOfAllScenarioResult==null){
				listOfAllScenarioResult= new ArrayList<ScenarioResultObject>();
			}}
			
			public static ScenarioResultCollection getInstance(){
				if(instance==null){
					instance=new ScenarioResultCollection();
					return instance;
				}
				else{
					return instance;
				}
			}
			
			public Boolean addIntoResultCollection(ScenarioResultObject obj){
				if(obj instanceof ScenarioResultObject){
				return listOfAllScenarioResult.add(obj);
				}
				return false;
			}
		
			
			
			
}
