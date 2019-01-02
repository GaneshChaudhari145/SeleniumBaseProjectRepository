package com.aqm.staf.framework.core.exceptions;

@SuppressWarnings("serial")
public class ElementNotClickableException extends RuntimeException {

    String strValue;

    public ElementNotClickableException(String element) {
        super(
                "Element "
                        + element
                        + " is not clickable \n"
                        + "Possible reasons 1.Pages not loaded properly due to performance issue \n 2.Element is not in current view port"
                        + " kindly refer attached screenshot for more clearification");
    }

    public ElementNotClickableException(String element, Throwable cause) {
        super(
                "Element "
                        + element
                        + " is not clickable \n"
                        + "Possible reasons 1.Pages not loaded properly due to performance issue \n 2.Element is not in current view port"
                        + " kindly refer attached screenshot for more clearification",
                cause);
    }

    public ElementNotClickableException() {
        super(
                "Element "
                        + " is not clickable \n"
                        + "Possible reasons 1.Pages not loaded properly due to performance issue \n 2.Element is not in current view port"
                        + " kindly refer attached screenshot for more clearification");
    }

}
