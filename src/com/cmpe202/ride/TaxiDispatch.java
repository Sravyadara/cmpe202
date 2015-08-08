package com.cmpe202.ride;

import java.sql.SQLException;
import java.util.ArrayList;

import com.cmpe202.member.Driver;

public class TaxiDispatch implements DispatchStrategyInterface {

	public Driver searchDriver(Ride ride) throws SQLException {

		DispatchDAO dispatchDAO = new DispatchDAO();
		ArrayList<Driver> freeDrivers = new ArrayList<Driver>();
		Driver driver = null;

		// select driver whoose availability status is free and driver type is
		// taxi

		freeDrivers = dispatchDAO.getFreeDrivers(ride.getRidetype());
		if (freeDrivers.size() != 0) {
			driver = freeDrivers.get(0);

			// update or insert into dispatch table
			String member_emailId = dispatchDAO.getCustomerByEmail(ride
					.getRequestid());
			if (driver != null && member_emailId != null) {
				int status = dispatchDAO.insertDispatch(ride.getRideid(),
						driver.getMemberid(), driver.getVehicleId(),
						member_emailId);
				if (status != 0) {
					// notify customer and driver
					System.out
							.println("Customer-Notification:-----Your ride will reach you at  "
									+ ride.getPickuptime());
					System.out
							.println("Driver-Notification:-----Your ride is waiting at "
									+ ride.getPickuplocation()
									+ " with Customer email: " + member_emailId);
				}
				// update vehicle and driver status
				dispatchDAO
						.updateDriverStatus("assigned", driver.getMemberid());
			} 
		}
		return driver;
	}

}