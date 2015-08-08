package com.cmpe202.request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cmpe202.member.Member;
import com.cmpe202.ride.Dispatch;

public class DBQuery {
	private Connection connection;
	private Statement statement;

	public DBQuery() {
	}

	public int updateStartTime(String StartTime, int requestId)
			throws SQLException {

		/*System.out
				.println("------Entered into updateStartTime() method in DataAccess------");*/
		int status = 0;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.createStatement();
			String sql;
			sql = " UPDATE ride SET pickuptime='" + StartTime
					+ "' WHERE requestid='" + requestId + "'";
			/*System.out.println("Update Start Time in ride table: " + sql);*/
			status = statement.executeUpdate(sql);
			connection.close();
		} catch (Exception e) {
			/*System.out.println("ERROR while updating start Time: "
					+ e.getMessage());*/
		} finally {
			connection.close();
		}
		/*System.out
				.println("------Exit from updateStartTime() method in DataAccess------");*/
		return status;
	}

	public int updateEndTime(String endTime, int requestId) throws SQLException {

		/*System.out
				.println("------Entered into updateEndTime() method in DataAccess------");*/
		int status = 0;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.createStatement();
			String sql;
			sql = " UPDATE ride SET endtime='" + endTime
					+ "' WHERE requestid='" + requestId + "'";
			/*System.out.println("Update end Time in ride table: " + sql);*/
			status = statement.executeUpdate(sql);
			connection.close();
		} catch (Exception e) {
			/*System.out.println("ERROR while updating end Time: "
					+ e.getMessage());*/
		} finally {
			connection.close();
		}
		/*System.out
				.println("------Exit from updateEndTime() method in DataAccess------");*/
		return status;
	}

	public int updateRideStatus(Dispatch rideState, int requestId)
			throws SQLException {

		/*System.out
				.println("------Entered into updateRideStatus() method in DataAccess------");*/
		int status = 0;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.createStatement();
			String sql;
			sql = " UPDATE ride SET ridestate=" + rideState
					+ " WHERE requestid=" + requestId + "";
			/*System.out.println("Update ridestate in ride table: " + sql);*/
			status = statement.executeUpdate(sql);
			connection.close();
		} catch (Exception e) {
			/*System.out.println("ERROR while updating ridestate: "
					+ e.getMessage());*/
		} finally {
			connection.close();
		}
		/*System.out
				.println("------Exit from updateRideStatus() method in DataAccess------");*/
		return status;
	}

	public int requestCount() throws SQLException {

		/*System.out
				.println("------Entered into requestCount() method in DataAccess------");*/
		int status = 0;
		int requestCount = 0;
		ResultSet rs = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.createStatement();
			String sql;
			sql = "SELECT COUNT(*) FROM request WHERE request_state = 'Initiated'";
			rs = statement.executeQuery(sql);
			// get the number of rows from the result set
			rs.next();
			requestCount = rs.getInt(1);
			/*System.out.println("Get request count from request table: " + sql);*/
			status = statement.executeUpdate(sql);
			connection.close();
		} catch (Exception e) {
			/*System.out.println("ERROR : " + e.getMessage());*/
		} finally {
			connection.close();
		}
		/*System.out
				.println("------Exit from requestCount() method in DataAccess------");*/
		return requestCount;

	}

	public int driverCount() throws SQLException {

		/*System.out
				.println("------Entered into driverCount() method in DataAccess------");*/
		int status = 0;
		int driverCount = 0;
		ResultSet rs = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.createStatement();
			String sql;
			sql = "SELECT COUNT(*) FROM driver";
			rs = statement.executeQuery(sql);
			// get the number of rows from the result set
			rs.next();
			driverCount = rs.getInt(1);
			/*System.out.println("Get driver count from driver table: " + sql);*/
			status = statement.executeUpdate(sql);
			connection.close();
		} catch (Exception e) {
			/*System.out.println("ERROR : " + e.getMessage());*/
		} finally {
			connection.close();
		}
		/*System.out
				.println("------Exit from driverCount() method in DataAccess------");*/
		return driverCount;

	}

}
