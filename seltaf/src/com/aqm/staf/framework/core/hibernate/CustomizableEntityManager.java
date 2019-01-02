package com.aqm.staf.framework.core.hibernate;

import org.hibernate.mapping.Component;

public interface CustomizableEntityManager {
	public static String CUSTOM_COMPONENT_NAME = "customProperties";

	void addCustomField(String name);

	void removeCustomField(String name);

	Component getCustomProperties();

	Class getEntityClass();
}