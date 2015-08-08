package com.cmpe202.ride;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cmpe202.member.Driver;
import com.cmpe202.member.Member;
import com.cmpe202.notifications.Notifications;

public class RideShareDispatch implements DispatchStrategyInterface {

	public Driver searchDriver(Ride ride) throws SQLException {

		// set customer coordinates based on pick up location - random set
		int upper = 20;
		int lower = 0;
		String driverId = null;
		HashMap<String, Point> driverCoordinates = new HashMap<String, Point>();
		HashMap<String, Double> distances = new HashMap<String, Double>();
		DispatchDAO dispatchDAO = new DispatchDAO();
		Driver driver = null;
		double totalTime = 0.0;

		ride.getPickuplocation();

		float x = (int) (Math.random() * (upper - lower)) + lower;

		float y = (int) (Math.random() * (upper - lower)) + lower;

		Point customerPoint = new Point();
		customerPoint.setX(x);
		customerPoint.setY(y);

		// get existing coordinates of all the drivers from the database
		driverCoordinates = dispatchDAO.getAllDriversLocation(ride
				.getRidetype());

		if (driverCoordinates.size() != 0) {
			// calculate distance between two coordinates
			for (Map.Entry<String, Point> entry : driverCoordinates.entrySet()) {
				distances
						.put(entry.getKey(),
								Math.sqrt(Math
										.pow((entry.getValue().getX() - customerPoint
												.getX()), 2)
										+ Math.pow(
												(entry.getValue().getY() - customerPoint
														.getY()), 2)));
			}
			Map<String, Double> sortedDistance = sortByComparator(distances);

			// find the driver with least distance
			for (Map.Entry<String, Double> entry : sortedDistance.entrySet()) {
				driverId = entry.getKey();
				totalTime = entry.getValue();
				break;
			}

			// insert into dispatch table
			// --get customer email id from member
			// --get driver details based on driver_email_id
			driver = dispatchDAO.getDriverByEmailId(driverId);
			String member_emailId = dispatchDAO.getCustomerByEmail(ride
					.getRequestid());

			if (driver != null && member_emailId != null) {
				int status = dispatchDAO.insertDispatch(ride.getRideid(),
						driver.getMemberid(), driver.getVehicleId(),
						member_emailId);

				if (status != 0) {
					// notify customer and driver
					/*
					 * System.out.println(
					 * "Customer-Notification:-----Your ride will reach you in "
					 * +totalTime*5 + " mins"); System.out.println(
					 * "Driver-Notification:-----Your ride is waiting at "
					 * +ride.getPickuplocation() +
					 * " with Customer email: "+member_emailId);
					 */

					Notifications notification = new Notifications();
					notification
							.sendMessage("Customer-Notification:-----Your ride will reach you in "
									+ totalTime * 5 + " mins");
					notification
							.sendMessage("Driver-Notification:-----Your ride is waiting at "
									+ ride.getPickuplocation()
									+ " with Customer email: " + member_emailId);
				}
				// update vehicle and driver status
				dispatchDAO.updateDriverStatus("assigned", driverId);
			} 

		} 

		return driver;
	}

	private static Map<String, Double> sortByComparator(
			Map<String, Double> distances) {

		// Convert Map to List
		List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(
				distances.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
					Map.Entry<String, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
		for (Iterator<Map.Entry<String, Double>> it = list.iterator(); it
				.hasNext();) {
			Map.Entry<String, Double> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

}