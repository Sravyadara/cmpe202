package com.cmpe202.ride;

import com.cmpe202.ride.EngagedState;
import com.cmpe202.ride.Ride;

public class WaitingState implements RideStateInterface{

private Dispatch rideStateContext;
	
	public WaitingState(Dispatch d){
		rideStateContext = d;
	}

	@Override
	public void initiateRide() {
		// TODO Auto-generated method stub
		rideStateContext.setRideState(new EngagedState(rideStateContext));
	}

	@Override
	public int RideInTransit() {
		// TODO Auto-generated method stub
		System.out.println("Ride should be initiated first");
		return 0;
		
	}

	@Override
	public int concludeRide() {
		// TODO Auto-generated method stub
		System.out.println("Ride should be initiated first");
		return 0;
		
	}

}
