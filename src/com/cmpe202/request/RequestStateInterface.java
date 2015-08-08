package com.cmpe202.request;

import com.cmpe202.ride.Ride;

public interface RequestStateInterface {

	public String receiveRequest(String memberId, String requestType,
			String ridetype, String pickuploc, String dropoffloc,
			String vehicletype, String pickuptime, int noofseats);

	public String receiveRequest(String memberId, String requestType);

	// public String processRequest(String requestType, Ride ride);
	public String processRequest(String requestType);

	public String concludeRequest();

	public String cancelRequest();

}