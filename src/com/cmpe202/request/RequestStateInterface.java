package com.cmpe202.request;

public interface RequestStateInterface {
	
	public String receiveRequest();
	public String processRequest(String requestType);
	public String cancelRequest();

}
