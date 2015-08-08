package com.cmpe202.request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.cmpe202.member.Member;
import com.cmpe202.rules.MemberRules;
import com.cmpe202.rules.Rules;

public class SignUpForm {
	private Connection SignUpconnection;
	private Statement Signstatement;

	public String signUp() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		String name, contactno, memberId, password, address, role, paymentcardNo, expdate, cvv, licenseno = null, ssn = null, executive = null;
		int noofseats;
		System.out
				.println("***********************************************************\n");
		System.out
				.println("                   Welcome to SignUp form                  \n");
		System.out
				.println("***********************************************************\n");
		System.out.println("Enter Name:");
		name = scanner.nextLine();
		System.out.println("Enter contactno(999-999-9999):");
		contactno = scanner.nextLine();
		System.out.println("Enter MemberID(EmailId):");
		memberId = scanner.nextLine();
		System.out.println("Enter Password:");
		password = scanner.nextLine();
		System.out.println("Enter Address:");
		address = scanner.nextLine();
		System.out.println("Enter Payment Card No:");
		paymentcardNo = scanner.nextLine();
		System.out.println("Enter Expiry Date (YYYY-MM-DD):");
		expdate = scanner.nextLine();
		System.out.println("Enter CVV:");
		cvv = scanner.nextLine();
		System.out.println("Select Role");
		System.out.println("a. Customer");
		System.out.println("b. Driver");
		System.out.println("c. Executive");
		role = scanner.nextLine();
		if (role.equalsIgnoreCase("a")) {

			role = "customer";
		} else if (role.equalsIgnoreCase("b")) {
			role = "driver";
			System.out.println("Enter License No:");
			licenseno = scanner.nextLine();
			System.out.println("Enter SSN(Last 4 digits):");
			ssn = scanner.nextLine();
			System.out.println("Enter Ur Executive");
			executive = scanner.nextLine();
			System.out.println("Enter Driver type");
			System.out.println("1. Taxi");
			System.out.println("2. Ride Share");
			String drivetype;
			String type = scanner.nextLine();
			if (type.equals("2")) {
				drivetype = "Ride Share";
				String vehiclemodel, vehicletype;
				int seats;
				System.out.println("Enter Vehicle Model");
				vehiclemodel = scanner.nextLine();
				System.out.println("Enter Vehicle Type");
				vehicletype = scanner.nextLine();
				System.out.println("Enter No of Seats");
				seats = scanner.nextInt();

				AddVehicle(vehicletype, vehiclemodel, seats);
				int vehicleid = getMaxVehicleId();
				insertDriver(memberId, vehicleid, drivetype);
			} else {
				drivetype = "Taxi";
				String vehicleno, vehiclemodel, vehicletype;
				int vehicleid = AssignVehicletoDriver();
				insertDriver(memberId, vehicleid, drivetype);

			}

		}

		else if (role.equalsIgnoreCase("c")) {
			role = "executive";
		}
		Rules memberrules = new MemberRules(memberId, licenseno, role);
		if (memberrules.validate()) { 
			try {
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				// get current date time with Date()
				Date date = new Date();
				String reqdate = dateFormat.format(date).toString();
				SignUpconnection = ConnectionFactory.getConnection();
				Signstatement = SignUpconnection.createStatement();
				String sql = "INSERT INTO member(member_emailid,role,manager,name,address,"
						+ "contactno,password,paymentcard,cvv,expirydate,Licenseno,ssn) VALUES "
						+ "( " + "\""
						+ memberId
						+ "\""
						+ ","
						+ "\""
						+ role
						+ "\""
						+ ","
						+ "\""
						+ executive
						+ "\""
						+ ","
						+ "\""
						+ name
						+ "\""
						+ ","
						+ "\""
						+ address
						+ "\""
						+ ","
						+ "\""
						+ contactno
						+ "\""
						+ ","
						+ "\""
						+ password
						+ "\""
						+ ","
						+ "\""
						+ paymentcardNo
						+ "\""
						+ ","
						+ "\""
						+ cvv
						+ "\""
						+ ","
						+ "\""
						+ expdate
						+ "\""
						+ ","
						+ "\""
						+ licenseno
						+ "\""
						+ "," + "\"" + ssn + "\"" + ")";
				Signstatement.executeUpdate(sql);

			} finally {
				DbUtil.close(Signstatement);
				DbUtil.close(SignUpconnection);
			}
			System.out
					.println("Registration is successful, Please use your MemberId and Password as login credentials ");
			return memberId;
		} else {
			return "Error";
		}
	}

	public int AddVehicle(String vehicleType, String vehicle_model,
			int requestNoOfSeats) throws SQLException {
		String query = "INSERT INTO vehicle(vehiclemodel,vehicletype,noofseats) VALUES "
				+ "( "
				+ "\""
				+ vehicle_model
				+ "\""
				+ ","
				+ "\""
				+ vehicleType
				+ "\"" + "," + requestNoOfSeats + ")";
		int status = 0;
		try {
			SignUpconnection = ConnectionFactory.getConnection();
			Signstatement = SignUpconnection.createStatement();
			status = Signstatement.executeUpdate(query);

		} finally {
			DbUtil.close(Signstatement);
			DbUtil.close(SignUpconnection);
		}
		return status;
	}

	public int getMaxVehicleId() throws SQLException {
		int vehicleid = 0;
		String query = "select * from vehicle";
		ResultSet rs = null;
		Member member = null;
		try {
			SignUpconnection = ConnectionFactory.getConnection();
			Signstatement = SignUpconnection.createStatement();
			rs = Signstatement.executeQuery(query);
			while (rs.next()) {

				vehicleid++;

			}
		} finally {
			DbUtil.close(rs);
			DbUtil.close(Signstatement);
			DbUtil.close(SignUpconnection);
		}
		return vehicleid;
	}

	public int insertDriver(String driverId, int vehicleId, String driver_type)
			throws SQLException {
		String location = getlocation();
		String driveravailability = "free";
		String query = "INSERT INTO driver(driver_emailid,driver_vehicleid,driverlocation,driverstatus,driver_type) VALUES "
				+ "( '"
				+ driverId
				+ "','"
				+ vehicleId
				+ "','"
				+ location
				+ "','" + driveravailability + "','" + driver_type + "'" + ")";
		int status = 0;
		try {
			SignUpconnection = ConnectionFactory.getConnection();
			Signstatement = SignUpconnection.createStatement();
			status = Signstatement.executeUpdate(query);

		} finally {
			DbUtil.close(Signstatement);
			DbUtil.close(SignUpconnection);
		}
		return status;
	}

	public int AssignVehicletoDriver() throws SQLException {
		String query = "select vehicleid from vehicle where vehicleid NOT IN (SELECT driver_vehicleid FROM driver)";
		ResultSet rs = null;
		int status = 0;
		int vehicleId = 0;
		try {
			SignUpconnection = ConnectionFactory.getConnection();
			Signstatement = SignUpconnection.createStatement();
			rs = Signstatement.executeQuery(query);
			if (rs.next()) {
				vehicleId = rs.getInt("vehicleid");
			}

		} finally {
			DbUtil.close(rs);
			DbUtil.close(Signstatement);
			DbUtil.close(SignUpconnection);
		}
		return vehicleId;
	}

	public String getlocation() {
		int upper = 20;
		int lower = 0;

		int x = (int) (Math.random() * (upper - lower)) + lower;

		int y = (int) (Math.random() * (upper - lower)) + lower;

		String location = x + "," + y;
		return location;
	}

}