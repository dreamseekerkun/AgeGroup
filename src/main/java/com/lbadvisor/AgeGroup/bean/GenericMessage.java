package com.lbadvisor.AgeGroup.bean;

public class GenericMessage extends Message {
	
	public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private int errorCode;
}
