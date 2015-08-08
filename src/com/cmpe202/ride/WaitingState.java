package com.cmpe202.ride;

import java.util.HashMap;

import com.cmpe202.ride.EngagedState;
import com.cmpe202.ride.Ride;

public class WaitingState implements RideStateInterface{

private Dispatch dispatchStateContext;
	
	public WaitingState(Dispatch d){
		dispatchStateContext = d;
	}

	@Override
	public void initiateRide() {
		// TODO Auto-generated method stub
	dispatchStateContext.setRideState(new EngagedState(dispatchStateContext));
	}

	@Override
	public int RideInTransit(HashMap<String, String> rideDetails) {
		// TODO Auto-generated method stub
		System.out.println("Ride should be initiated first");
		return 0;
		
	}

	@Override
	public int concludeRide(HashMap<String, String> rideDetails) {
		// TODO Auto-generated method stub
		System.out.println("Ride should be initiated first");
		return 0;
		
	}

}
