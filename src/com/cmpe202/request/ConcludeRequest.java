package com.cmpe202.request;

import com.cmpe202.ride.Ride;

public class ConcludeRequest implements RequestStateInterface {

	private Request request;

	public ConcludeRequest(Request r) {
		request = r;
	}

	public String receiveRequest(String memberId, String requestType,
			String ridetype, String pickuploc, String dropoffloc,
			String vehicletype, String pickuptime, int noofseats) {
		return "The request is processed";
	}

	public String receiveRequest(String memberId, String requestType) {
		return "The request is processed";
	}

	public String processRequest(String requestType) {
		return "The request is processed";
	}

	public String concludeRequest() {
		request.setRequestState(new InitiateRequest(request));
		return "The request is served";
	}

	public String cancelRequest() {
		request.setRequestState(new InitiateRequest(request));
		return "The request has been Cancelled";
	}

}