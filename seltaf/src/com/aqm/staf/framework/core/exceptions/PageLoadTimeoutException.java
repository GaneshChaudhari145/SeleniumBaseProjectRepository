package com.aqm.staf.framework.core.exceptions;

@SuppressWarnings("serial")
public class PageLoadTimeoutException extends RuntimeException {

    String strValue;

    public PageLoadTimeoutException(String message, String timeout) {
        super(
                message
                        + " is not loaded in specified timeout of "
                        + timeout
                        + " Seconds kindly refer attached screenshot for more clearification");
    }

    public PageLoadTimeoutException(String value, String timeout,
                                    Exception cause) {
        super(
                value
                        + " is not loaded in specified timeout of "
                        + timeout
                        + " Seconds kindly refer attached screenshot for more clearification",
                cause);

    }

    public PageLoadTimeoutException() {
        super("Web page" + " is not loaded in specified timeout "
                + "kindly refer attached screenshot for more clearification");
    }

}
