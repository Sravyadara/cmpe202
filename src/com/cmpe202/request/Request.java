package com.cmpe202.request;

public class Request {

	private String requestType;
	RequestStateInterface requestState;
	
	public Request(){
		requestState = new InitiateRequest(this);
	}
	
	public void processRequest(){
		
	}
	public void cancelRequest(){
		
	}
	public void receiveRequest(){
		
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public RequestStateInterface getRequestState() {
		return requestState;
	}

	public void setRequestState(RequestStateInterface requestState) {
		this.requestState = requestState;
	}
}
