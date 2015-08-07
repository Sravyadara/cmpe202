package com.cmpe202.request;

public interface RequestStateInterface {
	
	public String receiveRequest(String memberId,String requestType);
	public String processRequest(String requestType);
	public String cancelRequest();

}
