package com.aqm.staf.framework.core.exceptions;

@SuppressWarnings("serial")
public class InvalidDataException extends RuntimeException {

    String strValue;

    public InvalidDataException(String message) {
        super("Please make sure that currect data " + message
                + " is provided in excel and same data is uploaded to DataBase");
    }


    public InvalidDataException(String message, Throwable cause) {
        super("Please make sure that currect data " + message
                + " is provided in excel and same data is uploaded to DataBase", cause);
    }

    public InvalidDataException() {
        super("Please make sure that currect data is provided in excel and same data is uploaded in DB");

    }


}
