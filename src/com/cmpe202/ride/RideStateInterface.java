package com.cmpe202.ride;

import java.util.Date;
import java.util.HashMap;

public interface RideStateInterface {

	public void initiateRide();

	public int RideInTransit(HashMap<String, String> rideDetails);

	public int concludeRide(HashMap<String, String> rideDetails);

}
