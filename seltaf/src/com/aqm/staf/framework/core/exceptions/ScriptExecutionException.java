package com.aqm.staf.framework.core.exceptions;

import com.aqm.tests.AutomationDriverScript;

@SuppressWarnings("serial")
public class ScriptExecutionException extends RuntimeException {
    public ScriptExecutionException(String message) {
        super(message);
        AutomationDriverScript.sroForReference.setReasonIfFailed(message);
    }

    public ScriptExecutionException(String message, Exception cause) {
        super(message, cause);
        AutomationDriverScript.sroForReference.setReasonIfFailed(message);
    }
}
