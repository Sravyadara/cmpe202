package com.cmpe202.ride;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.cmpe202.request.ConnectionFactory;
import com.cmpe202.request.DbUtil;
import com.cmpe202.ride.Ride;

public class RideDAO {
	private Connection connection;
	private Statement statement;

	public RideDAO() {
	}

	public Ride getRideDetails(int RideId) throws SQLException {
		String query = "SELECT * FROM ride where rideid=\"" + RideId + "\"";
		ResultSet rs = null;
		Ride ride = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				ride = new Ride();
				ride.setRideid(rs.getInt("rideid"));
				ride.setRequestid(rs.getInt("requestid"));
				ride.setRidetype(rs.getString("ridetype"));
				ride.setPickuplocation(rs.getString("pickuplocation"));
				ride.setDropofflocation(rs.getString("dropofflocation"));
				ride.setPickuptime(rs.getString("pickuptime"));
				ride.setEndtime(rs.getString("endtime"));
				ride.setRidestate(rs.getString("ridestate"));
				ride.setNoofseats(rs.getInt("noofseats"));
				ride.setVehicletype(rs.getString("vehicletype"));

			}
		} finally {
			DbUtil.close(rs);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return ride;
	}

}
