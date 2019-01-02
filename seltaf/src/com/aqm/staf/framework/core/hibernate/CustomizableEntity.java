package com.aqm.staf.framework.core.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.mapping.PersistentClass;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.EntityPersister;

import com.aqm.staf.library.databin.GenericEntity;

//public interface CustomizableEntity {
public class CustomizableEntity implements Cloneable {
	
	public static String runningElement="";
	public static String runningRowReference="";
	public static String runningSheetName="";
	private GenericEntity GenericEntityInstance;
	public static ArrayList<String> listOfSheetName=new ArrayList<String>();
	
	private Map customProperties;

	public CustomizableEntity(String runningSheetName){
		if(!runningSheetName.isEmpty())
			if(!listOfSheetName.contains(runningSheetName))
		listOfSheetName.add(runningSheetName);
	}
	
	public CustomizableEntity(){
		
	}
	
	
	public Map getCustomProperties() {
		if (customProperties == null)
			customProperties = new HashMap();
		return customProperties;
	}

	public void setCustomProperties(Map customProperties) {
		this.customProperties = customProperties;
	}

	public int getIntegerValueForField(String fieldName) {
		runningElement=fieldName;
		runningSheetName=this.getClass().getName();
		try {
			GenericEntityInstance= (GenericEntity) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		runningRowReference=GenericEntityInstance.getReference();
		return ((Integer) getCustomProperties().get(fieldName)).intValue();
	}

	public void setIntegerValueForField(String name, int value) {
		Map toCheckKey = getCustomProperties();
		if (toCheckKey.containsKey(name)) {
			toCheckKey.put(name, new Integer(value));
		}
	}

	public long getLongValueForField(String fieldName) {

		runningElement=fieldName;
		
		runningSheetName=this.getClass().getName();
		try {
			GenericEntityInstance= (GenericEntity) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		runningRowReference=GenericEntityInstance.getReference();
		return ((Long) getCustomProperties().get(fieldName)).longValue();
	}

	public void setLongValueForField(String name, long value) {
		Map toCheckKey = getCustomProperties();
		if (toCheckKey.containsKey(name)) {
			toCheckKey.put(name, new Long(value));
		}
	}

	public float getFloatValueForField(String fieldName) {

		runningElement=fieldName;
		runningSheetName=this.getClass().getName();

		try {
			GenericEntityInstance= (GenericEntity) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		runningRowReference=GenericEntityInstance.getReference();
		return ((Float) getCustomProperties().get(fieldName)).floatValue();
	}

	public void setFloatValueForField(String name, float value) {
		Map toCheckKey = getCustomProperties();
		if (toCheckKey.containsKey(name)) {
			toCheckKey.put(name, new Float(value));
		}
	}

	public double getDoubleValueForField(String fieldName) {

		runningElement=fieldName;
		runningSheetName=this.getClass().getName();
		
		try {
			GenericEntityInstance= (GenericEntity) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		runningRowReference=GenericEntityInstance.getReference();
		return ((Double) getCustomProperties().get(fieldName)).doubleValue();
	}

	public void setDoubleValueForField(String name, double value) {
		Map toCheckKey = getCustomProperties();
		if (toCheckKey.containsKey(name)) {
			toCheckKey.put(name, new Double(value));
		}
	}

	public boolean getBooleanValueForField(String fieldName) {
		
		Map toCheckKey = getCustomProperties();
		if (toCheckKey.containsKey(fieldName)) {
			runningElement=fieldName;
			runningSheetName=this.getClass().getName();
			try {
				GenericEntityInstance= (GenericEntity) this.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			runningRowReference=GenericEntityInstance.getReference();
			return ((Boolean) toCheckKey.get(fieldName)).booleanValue();
		}
		return false;
	}
	

	public void setBooleanValueForField(String name, boolean value) {
		Map toCheckKey = getCustomProperties();
		if (toCheckKey.containsKey(name)) {
			toCheckKey.put(name, new Boolean(value));
		}
	}

	public String getStringValueForField(String fieldName) {

		runningElement=fieldName;
		runningSheetName=this.getClass().getName();
		try {
			GenericEntityInstance= (GenericEntity) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		runningRowReference=GenericEntityInstance.getReference();
		return (String) getCustomProperties().get(fieldName);
	}

	public void setStringValueForField(String name, String value) {
		Map toCheckKey = getCustomProperties();
		if (toCheckKey.containsKey(name)) {
			toCheckKey.put(name, value);
		}
	}

	public Date getDateTimeValueForField(String fieldName) {

		runningElement=fieldName;
		runningSheetName=this.getClass().getName();
		try {
			GenericEntityInstance= (GenericEntity) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		runningRowReference=GenericEntityInstance.getReference();
		return (Date) getCustomProperties().get(fieldName);
	}

	public void setDateTimeValueForField(String name, Date value) {
		Map toCheckKey = getCustomProperties();
		if (toCheckKey.containsKey(name)) {
			toCheckKey.put(name, value);
		}
	}

	public double getCharacterValueForField(String fieldName) {

		runningElement=fieldName;
		runningSheetName=this.getClass().getName();
		try {
			GenericEntityInstance= (GenericEntity) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		runningRowReference=GenericEntityInstance.getReference();
		return ((Character) getCustomProperties().get(fieldName)).charValue();
	}

	public void setCharatcterValueForField(String name, char value) {
		Map toCheckKey = getCustomProperties();
		if (toCheckKey.containsKey(name)) {
			toCheckKey.put(name, new Character(value));
		}
	}

	public Object getValueOfCustomField(String name) {
		runningElement=name;
		runningSheetName=this.getClass().getName();
		try {
			GenericEntityInstance= (GenericEntity) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		runningRowReference=GenericEntityInstance.getReference();
		return getCustomProperties().get(name);
	}

	public void setValueOfCustomField(String name, Object value) {
		Map toCheckKey = getCustomProperties();
		if (toCheckKey.containsKey(name)) {
			toCheckKey.put(name, value);
		}
	}
}