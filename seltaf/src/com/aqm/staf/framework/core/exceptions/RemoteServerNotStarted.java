package com.aqm.staf.framework.core.exceptions;

@SuppressWarnings("serial")
public class RemoteServerNotStarted extends RuntimeException {

    String strValue;

    public RemoteServerNotStarted(Exception cause) {
        super("Grid Hub is not Started please start hub ", cause);

    }

    public RemoteServerNotStarted() {
        super("Grid Hub is  not Started please start hub");
    }

}
