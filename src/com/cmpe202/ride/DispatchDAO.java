package com.cmpe202.ride;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.cmpe202.payment.Coupon;
import com.cmpe202.request.ConnectionFactory;
import com.cmpe202.request.DbUtil;
import com.cmpe202.member.Driver;
import com.cmpe202.member.Member;

public class DispatchDAO {
	private Connection connection;
	private Statement statement;

	public HashMap<String, Point> getAllDriversLocation(String rideType)
			throws SQLException {
		String query = "SELECT * FROM driver WHERE driverstatus=" + "'free'"
				+ " AND driver_type= '" + rideType + "'";
		ResultSet rs = null;
		HashMap<String, Point> driverCoordinates = new HashMap<String, Point>();
		Point point;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				point = new Point();
				String[] xandy = rs.getString("driverlocation").split(",");
				point.setX(Integer.parseInt(xandy[0]));
				point.setY(Integer.parseInt(xandy[1]));
				// member.setLicenseDetails(rs.getString("employee_emailid"));
				driverCoordinates.put(rs.getString("driver_emailid"), point);

			}
		} finally {
			DbUtil.close(rs);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return driverCoordinates;
	}

	// to be used for inserting dispatch entry after assigning vehicle
	public int insertDispatch(int dispatch_rideid,
			String dispatch_driver_emailid, int dispatch_vehicleid,
			String dispatch_member_emailid) throws SQLException {
		String query = "INSERT INTO dispatch(dispatch_rideid,dispatch_driver_emailid,dispatch_vehicleid,dispatch_member_emailid) VALUES "
				+ "( "
				+ dispatch_rideid
				+ ",'"
				+ dispatch_driver_emailid
				+ "',"
				+ dispatch_vehicleid
				+ ",'"
				+ dispatch_member_emailid
				+ "')";
		int status = 0;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			status = statement.executeUpdate(query);

		} finally {
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return status;
	}

	// get driver based on driver_emailId
	public Driver getDriverByEmailId(String driver_emailid) throws SQLException {
		String query = "SELECT * FROM driver WHERE driver_emailid='"
				+ driver_emailid + "'";
		ResultSet rs = null;
		Driver driver = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				driver = new Driver();
				driver.setMemberid(rs.getString("driver_emailid"));
				driver.setVehicleId(rs.getInt("driver_vehicleid"));
				driver.setDriver_location(rs.getString("driverlocation"));
				driver.setDriverstatus(rs.getString("driverstatus"));
				driver.setDriver_type(rs.getString("driver_type"));
				// member.setLicenseDetails(rs.getString("employee_emailid"));

			}
		} finally {
			DbUtil.close(rs);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return driver;
	}

	// get driver based on driver_emailId
	public String getCustomerByEmail(int requestId) throws SQLException {
		String query = "SELECT * FROM request WHERE requestid=" + requestId;
		ResultSet rs = null;
		String member_emailId = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				member_emailId = rs.getString("member_emailid");
				// member.setLicenseDetails(rs.getString("employee_emailid"));

			}
		} finally {
			DbUtil.close(rs);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return member_emailId;
	}

	// update driver availability
	public int updateDriverStatus(String driverstatus, String driver_emailid)
			throws SQLException {
		String query = "UPDATE driver set driverstatus= '" + driverstatus
				+ "' WHERE driver_emailid='" + driver_emailid + "'";
		int status = 0;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			status = statement.executeUpdate(query);

		} finally {
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return status;
	}

	public ArrayList<Driver> getFreeDrivers(String rideType)
			throws SQLException {
		String query = "SELECT * FROM driver WHERE driverstatus=" + "'free'"
				+ "AND driver_type='" + rideType + "'";
		ResultSet rs = null;
		ArrayList<Driver> freeDrivers = new ArrayList<Driver>();
		Driver driver;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				driver = new Driver();
				driver.setMemberid(rs.getString("driver_emailid"));
				driver.setVehicleId(rs.getInt("driver_vehicleid"));
				driver.setDriver_location(rs.getString("driverlocation"));
				driver.setDriverstatus(rs.getString("driverstatus"));
				driver.setDriver_type(rs.getString("driver_type"));

				freeDrivers.add(driver);

			}
		} finally {
			DbUtil.close(rs);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return freeDrivers;
	}

	// get driver based on driver_emailId
	public Coupon getCoupon(String couponId) throws SQLException {
		String query = "SELECT * FROM coupon WHERE couponid="
				+ Integer.parseInt(couponId);
		ResultSet rs = null;
		Coupon coupon = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {

				coupon = new Coupon();
				coupon.setCouponId(rs.getString("couponid"));
				coupon.setDiscount((rs.getString("discount")));
				coupon.setCouponExpiry((rs.getString("couponexpiry")));
				// member.setLicenseDetails(rs.getString("employee_emailid"));

			}
		} finally {
			DbUtil.close(rs);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return coupon;
	}
}