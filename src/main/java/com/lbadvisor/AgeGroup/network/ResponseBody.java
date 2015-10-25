package com.lbadvisor.AgeGroup.network;

public class ResponseBody {
	private int status;
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	private Object result;
	private String errorMessage;
	
	public static class StatusCode {
        public static final int SUCCESS = 1;
        public static final int FAILURE = 2;
    }
	
	
	public ResponseBody(Object result) {
		this.result = result;
	}
	
	public ResponseBody(int status, Object result) {
		this.result = result;
		this.status = status;
	}
	
	public ResponseBody(int status, Object result, String errorMessage) {
		this.status = status;
		this.result = result;
		this.errorMessage = errorMessage;
	}
	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
