package com.cmpe202.member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cmpe202.request.ConnectionFactory;
import com.cmpe202.request.DbUtil;
import com.cmpe202.ride.Vehicle;
import com.cmpe202.member.Member;

public class MemberDAO {
	private Connection connection;
	private Statement statement;

	public MemberDAO() {
	}

	public Member getMember(String MemberId) throws SQLException {
		String query = "SELECT * FROM member where member_emailid=\""
				+ MemberId + "\"";
		ResultSet rs = null;
		Member member = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				member = new Member();
				member.setMemberid(rs.getString("member_emailid"));
				member.setPassword(rs.getString("password"));
				member.setRole(rs.getString("role"));
				member.setMemberName(rs.getString("name"));
				member.setAddress(rs.getString("address"));
				member.setContact(rs.getString("contactno"));
				member.setPaymentDetails(rs.getString("paymentcard"));
				member.setCvv(rs.getString("cvv"));
				member.setExpirydate(rs.getDate("expirydate"));
				member.setLicenseDetails(rs.getString("Licenseno"));
				member.setSsn(rs.getString("ssn"));
				member.setManager(rs.getString("manager"));

			}
		} finally {
			DbUtil.close(rs);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return member;
	}

	/*
	 * //to be used when the driver from member table has to be inserted into
	 * drivers table with vehicleId assigned. - ONLY FOR TAXI driver_type public
	 * int insertDriver(String driverId, String vehicleId, String
	 * currentLocation, String driverAvailabilityStatus, String driver_type)
	 * throws SQLException{
	 * 
	 * 
	 * String query =
	 * "INSERT INTO Driver(driver_emailid,driver_vehicleid,driverlocation,driverstatus,driver_type) VALUES "
	 * + "( "+driverId+ ","+vehicleId+","+ currentLocation + ","+
	 * driverAvailabilityStatus + "," + driver_type+ ","+")"; int status = 0;
	 * try { connection = ConnectionFactory.getConnection(); statement =
	 * connection.createStatement(); status = statement.executeUpdate(query);
	 * 
	 * } finally { DbUtil.close(statement); DbUtil.close(connection); } return
	 * status; }
	 * 
	 * //to be used for taxi driver by by assigning a free vehicle from vehicle
	 * table public int AssignVehicletoDriver() throws SQLException{ String
	 * query =
	 * "SELECT vehicleid FROM vehicle where vehicleid NOT IN (SELECT vehicleid FROM driver)"
	 * ; ResultSet rs = null; int status = 0; int vehicleId = 0; try {
	 * connection = ConnectionFactory.getConnection(); statement =
	 * connection.createStatement(); rs = statement.executeQuery(query); if
	 * (rs.next()) { vehicleId = rs.getInt("vehicleid"); }
	 * 
	 * } finally { DbUtil.close(rs); DbUtil.close(statement);
	 * DbUtil.close(connection); } return vehicleId; }
	 * 
	 * //to be used for admin to add new vehicle to inventory public int
	 * AddVehicle(String requestNoOfSeats, String vehicle_type, String
	 * vehicle_model, String vehiclespecification) throws SQLException{ String
	 * query =
	 * "INSERT INTO vehicle(noofseats,vehiclemodel,vehicletype,vehiclespecification) VALUES "
	 * + "( "+requestNoOfSeats+ ","+vehicle_model+","+ vehicle_type + ","+
	 * vehiclespecification+ ")"; int status = 0; try { connection =
	 * ConnectionFactory.getConnection(); statement =
	 * connection.createStatement(); status = statement.executeUpdate(query);
	 * 
	 * } finally { DbUtil.close(statement); DbUtil.close(connection); } return
	 * status; }
	 */
	// to be used for admin to update new vehcile in the inventory
	public int UpdateVehicle() throws SQLException {
		String query = "update query";
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

	// to be used for admin to remove vehicle from inventory
	public int removeVehicle(String vehicleId) throws SQLException {
		String query = "DELETE FROM vehicle WHERE vehicleid=" + vehicleId;
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
	
	public int AddNewVehicle(Vehicle vehicle) throws SQLException{
        String query = "INSERT INTO vehicle(noofseats,vehiclemodel,vehicletype,vehiclespecification) VALUES "
                + "( "+vehicle.getNoOfSeats()+ ",'"+vehicle.getVehicle_model()+"','"+ vehicle.getVehicleType() + "','"+ vehicle.getVehicle_spec()+ "')";
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
    
    //to be used for admin to update new vehcile in the inventory
    public int UpdateVehicle(Vehicle vehicle) throws SQLException{
        String query = " UPDATE vehicle SET noofseats="+vehicle.getNoOfSeats()+ ",vehiclemodel='" +vehicle.getVehicle_model()+ "',vehicletype='" +vehicle.getVehicleType()+"',vehiclespecification='" +vehicle.getVehicle_spec()+"' WHERE vehicleid="+vehicle.getVehicleId();
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
    
    //to be used for admin to remove vehicle from inventory
    public int removeVehicle(int vehicleId) throws SQLException{
        String query = "DELETE FROM vehicle WHERE vehicleid="+vehicleId;
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

}
