package com.cmpe202.ride;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import com.cmpe202.request.RideDAO;




public class ReleasedState implements RideStateInterface{
	
	private Dispatch rideStateContext;
	
	public ReleasedState(Dispatch d){
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
		System.out.println("Ride is already in Transit");
		return 0;
		
	}

	@Override
	public int concludeRide() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		 int eTime = (int) cal.getTimeInMillis();
		 RideDAO rideDAO = new RideDAO();
		 try {
			rideDAO.updateEndTime(eTime, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 try {
			rideDAO.updateRideStatus(rideStateContext, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	//	 rideStateContext.setEndTime(eTime);
		return eTime;
        
		
			
	}

}
