package com.cmpe202.request;

public class InitiateRequest implements RequestStateInterface{
	
	private Request request;
	
	public InitiateRequest(Request r){
		request = r;
	}

	public String receiveRequest() {
		// TODO Auto-generated method stub
		request.setRequestState(new ProcessRequest(request));
		return "Received a request";
	}

	public String processRequest(String requestType) {
		// TODO Auto-generated method stub
		return "Must receive a request first";
	}

	public String cancelRequest() {
		// TODO Auto-generated method stub
		return "Must receive a request first";
	}



}
