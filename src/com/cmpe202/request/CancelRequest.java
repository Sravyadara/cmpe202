package com.cmpe202.request;

public class CancelRequest implements RequestStateInterface{
	
	private Request request;
	
	public CancelRequest(Request r){
		request = r;
	}

	public String receiveRequest() {
		return "The request is cancelled";
	}

	public String processRequest(String requestType) {
		return "The request is cancelled";
	}

	public String cancelRequest() {
		request.setRequestState(new InitiateRequest(request));
		return "The request has been cancelled";
	}

}
