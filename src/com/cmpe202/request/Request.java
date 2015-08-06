package com.cmpe202.request;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.cmpe202.member.MemberDAO;
import com.cmpe202.member.Member;
import com.cmpe202.ride.Ride;

public class Request {

	private String requestType;
	RequestStateInterface requestState;
	
	public Request(){
		requestState = new InitiateRequest(this);
	}
	
	public void receiveRequest(String requestType){
		System.out.println(requestState.receiveRequest());
		System.out.println(requestState.processRequest(requestType));
		
	}
	/*public void processRequest(String requestType){
		System.out.println(requestState.processRequest(requestType));
	}*/
	public void cancelRequest(){
		System.out.println(requestState.cancelRequest());
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
