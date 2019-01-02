package com.aqm.staf.framework.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.mapping.Collection;

public class MapOfUserIDAssignedToAllocation {
	
	
	public static Map<String, String> listOfUserIDForExecution=null;
	private Properties prop;
	private InputStream input;

	private MapOfUserIDAssignedToAllocation() {
		if(listOfUserIDForExecution==null)
		MapOfUserIDAssignedToAllocation.listOfUserIDForExecution =Collections.synchronizedMap(MapOfUserID(UserIDStringSplitToArray(fetchingUserIDFromProperties())));
	}
	
	public static MapOfUserIDAssignedToAllocation getInstance(){
		return new MapOfUserIDAssignedToAllocation();
	}


	private String fetchingUserIDFromProperties() {
		String toReturn = "";
		try {
			loadProperties();
			toReturn = prop.getProperty("LoginUsers");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	private String[] UserIDStringSplitToArray(String strToSplit) {
		return strToSplit.split(",");
	}

	private HashMap<String,String> MapOfUserID(String[] arrayOfString) {
		HashMap<String,String> toReturn = new HashMap<String,String>();
		for (String s : arrayOfString) {
			toReturn.put(s,"Free");
		}
		return toReturn;
	}

	private void loadProperties() throws IOException {
		prop = new Properties();
		input = new FileInputStream("config.properties");
		prop.load(input);
	}
	
	

}