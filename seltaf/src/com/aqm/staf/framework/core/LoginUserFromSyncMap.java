package com.aqm.staf.framework.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class LoginUserFromSyncMap {

	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	public synchronized static String userIDForLogin(Map<String, String> listOfUserIDForExecution,String testScenarioReference) {
		String loginID = "";
		Collection<String> setOfUniqueUserID;
		setOfUniqueUserID = listOfUserIDForExecution.values();
		Iterator<String> it = setOfUniqueUserID.iterator();
		while (it.hasNext()) {
			if (it.next().equals("Free")) {
				loginID = getKeyByValue(listOfUserIDForExecution, "Free");
				listOfUserIDForExecution.put(loginID, testScenarioReference);
				break;
			} else {
				continue;
			}

		}
		System.out.println("");
		System.out.println("From Allocation Engine: <<< " + loginID
				+ " >>> assigned and Updated Status MAP::"
				+ listOfUserIDForExecution.toString());
		System.out.println("");
		return loginID;
	}

}
