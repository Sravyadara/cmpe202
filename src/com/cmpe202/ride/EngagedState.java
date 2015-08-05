package com.cmpe202.ride;

import java.util.Calendar;
import java.util.Date;

public class EngagedState implements RideStateInterface{
	
	private  Dispatch rideStateContext;
	

	
	public EngagedState(Dispatch d){
		rideStateContext = d;
	}

	@Override
	public void initiateRide() {
		// TODO Auto-generated method stub
		
		System.out.println("Ride is already initiated");
		
	}

	@Override
	public int RideInTransit() {
		// TODO Auto-generated method stub
		
		Calendar cal = Calendar.getInstance();
	   int sTime = (int) cal.getTimeInMillis();
        rideStateContext.setStartTime(sTime);
		return sTime;
       
	}	
	

	@Override
	public int concludeRide() {
		// TODO Auto-generated method stub
		
		System.out.println("Ride is in Transit");
		return 0;
		
	}
	
}
