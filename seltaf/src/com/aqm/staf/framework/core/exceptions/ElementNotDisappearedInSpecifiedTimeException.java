package com.aqm.staf.framework.core.exceptions;

@SuppressWarnings("serial")
public class ElementNotDisappearedInSpecifiedTimeException extends
        RuntimeException {

    public ElementNotDisappearedInSpecifiedTimeException(String element,
                                                         String timeout) {
        super(
                "Element "
                        + element
                        + " did not disapper in specified timeout of "
                        + timeout
                        + " seconds kindly refer attached screenshot for more clearification");
    }

    public ElementNotDisappearedInSpecifiedTimeException(String element,
                                                         String timeout, Throwable cause) {
        super(
                "Element "
                        + element
                        + " did not disapper in specified timeout of "
                        + timeout
                        + " seconds kindly refer attached screenshot for more clearification", cause);
    }


    public ElementNotDisappearedInSpecifiedTimeException(String element,
                                                         int timeout, Throwable cause) {
        super(
                "Element "
                        + element
                        + " did not disapper in specified timeout of "
                        + timeout
                        + " seconds kindly refer attached screenshot for more clearification", cause);
    }

    public ElementNotDisappearedInSpecifiedTimeException() {
        super("Element "

                + " did not disapper in specified timeout of "

                + " seconds kindly refer attached screenshot for more clearification");
    }

}
