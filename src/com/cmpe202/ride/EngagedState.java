package com.cmpe202.ride;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import com.cmpe202.member.Member;
import com.cmpe202.request.ConnectionFactory;
import com.cmpe202.request.DbUtil;
import com.cmpe202.request.RideDAO;



public class EngagedState implements RideStateInterface{
	
	private  Dispatch dispatchStateContext;
	private Connection connection;
	private Statement statement;
	private int sTime;
	

	
	public EngagedState(Dispatch d){
		dispatchStateContext = d;
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
	     sTime = (int) cal.getTimeInMillis();
       // dispatchStateContext.setStartTime(sTime);
        RideDAO rideDAO = new RideDAO();
        try {
			rideDAO.updateStartTime(sTime, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        dispatchStateContext.setRideState(new ReleasedState(dispatchStateContext));
		return sTime;
       
	}	
	

	@Override
	public int concludeRide() {
		// TODO Auto-generated method stub
		
		System.out.println("Ride is in Transit");
		return 0;
		
	}
	
	
	
}
