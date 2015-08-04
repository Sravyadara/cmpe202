package com.cmpe202.request;

public class CancelRequest implements RequestStateInterface{
	
	private Request request;
	
	public CancelRequest(Request r){
		request = r;
	}

	public String receiveRequest() {
		return null;
	}

	public String processRequest() {
		return null;
	}

	public String cancelRequest() {
		return null;
	}

}
