package com.cmpe202.ride;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.cmpe202.member.Member;
import com.cmpe202.request.ConnectionFactory;
import com.cmpe202.request.DbUtil;
import com.cmpe202.request.DBQuery;

public class EngagedState implements RideStateInterface {

	private Dispatch dispatchStateContext;
	private Connection connection;
	private Statement statement;
	private int sTime;

	public EngagedState(Dispatch d) {
		dispatchStateContext = d;
	}

	@Override
	public void initiateRide() {
		// TODO Auto-generated method stub

		System.out.println("Ride is already initiated");

	}

	@Override
	public int RideInTransit(HashMap<String, String> rideDetails) {
		// TODO Auto-generated method stub
		// Display Start Button
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the option to start the ride");
		System.out.println("1.Start the Ride");
		int menuItem = in.nextInt();
		if (menuItem == 1) {
			Calendar cal = Calendar.getInstance();

			// time in milliseconds
			sTime = (int) cal.getTimeInMillis();
			dispatchStateContext.setRideStartTime(sTime);
			/*System.out.println("Printing start time in milliseconds:" + sTime);*/

			// time in hh:mm
			final String startTime = new SimpleDateFormat("HH:mm").format(cal
					.getTime());
			/*System.out.println("Printing start time in hh:mm:" + startTime);*/
			DBQuery rideDAO = new DBQuery();
			try {
				rideDAO.updateStartTime(startTime,
						Integer.parseInt(rideDetails.get("requestId")));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			dispatchStateContext.setRideState(new ReleasedState(
					dispatchStateContext));

		}
		return sTime;
	}

	@Override
	public int concludeRide(HashMap<String, String> rideDetails) {
		// TODO Auto-generated method stub

		System.out.println("Ride is in Transit");
		return 0;

	}

}
