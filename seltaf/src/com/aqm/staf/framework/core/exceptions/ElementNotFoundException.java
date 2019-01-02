package com.aqm.staf.framework.core.exceptions;

@SuppressWarnings("serial")
public class ElementNotFoundException extends RuntimeException {

    String strValue;

    public ElementNotFoundException(String element) {
        super("Unable to Find " + element
                + " kindly refer attached screenshot for more clearification");
    }

    public ElementNotFoundException(String element, Throwable cause) {
        super("Unable to Find " + element
                + " kindly refer attached screenshot for more clearification",
                cause);
    }

    public ElementNotFoundException() {
        super("Unable to find the Element");
    }

}
