package com.cmpe202.ride;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.cmpe202.member.Member;
import com.cmpe202.payment.Cash;
import com.cmpe202.payment.CreditCard;
import com.cmpe202.payment.DebitCard;
import com.cmpe202.payment.Pass;
import com.cmpe202.payment.PayPal;
import com.cmpe202.payment.Payment;
import com.cmpe202.request.ConnectionFactory;
import com.cmpe202.request.DbUtil;

public class Ride extends Dispatch {

	private Connection rideconnection;
	private Statement ridestatement;

	protected int rideid;
	protected int requestid;
	protected String ridetype;
	protected String pickuplocation;
	protected String dropofflocation;
	protected String pickuptime;
	protected String endtime;
	protected String ridestate;
	protected int noofseats;
	protected String vehicletype;

	public Ride(Payment p) {
		super(p);
	}

	public Ride() {
		super();
	}

	public Connection getRideconnection() {
		return rideconnection;
	}

	public void setRideconnection(Connection rideconnection) {
		this.rideconnection = rideconnection;
	}

	public Statement getRidestatement() {
		return ridestatement;
	}

	public void setRidestatement(Statement ridestatement) {
		this.ridestatement = ridestatement;
	}

	public int getRideid() {
		return rideid;
	}

	public void setRideid(int rideid) {
		this.rideid = rideid;
	}

	public int getRequestid() {
		return requestid;
	}

	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}

	public String getRidetype() {
		return ridetype;
	}

	public void setRidetype(String ridetype) {
		this.ridetype = ridetype;
	}

	public String getPickuplocation() {
		return pickuplocation;
	}

	public void setPickuplocation(String pickuplocation) {
		this.pickuplocation = pickuplocation;
	}

	public String getDropofflocation() {
		return dropofflocation;
	}

	public void setDropofflocation(String dropofflocation) {
		this.dropofflocation = dropofflocation;
	}

	public String getPickuptime() {
		return pickuptime;
	}

	public void setPickuptime(String pickuptime) {
		this.pickuptime = pickuptime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getRidestate() {
		return ridestate;
	}

	public void setRidestate(String ridestate) {
		this.ridestate = ridestate;
	}

	public int getNoofseats() {
		return noofseats;
	}

	public void setNoofseats(int noofseats) {
		this.noofseats = noofseats;
	}

	public String getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}

	@Override
	public int pay(int amount, HashMap<String, String> paymentModeDetails) {
		int paymentStatusCode = payByMode(amount, paymentModeDetails);
		return paymentStatusCode;
	}

	@Override
	public long calculateAmount() {
		// get time from calcutateTime() method and calculate distance here.
		// After that call pay method which is above this one by sending amount
		// as argument to it.
		// That pay internally calls payByCalls().

		return 0;
	}

	public void insertRide(String memberId, String requestType, int reqId,
			String ridetype, String pickuploc, String dropoffloc,
			String pickuptime, int noofseats, String vehicletype)
			throws SQLException {

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// get current date time with Date()
			Date date = new Date();
			String reqdate = dateFormat.format(date).toString();
			String stareq = "Initiated";
			rideconnection = ConnectionFactory.getConnection();
			ridestatement = rideconnection.createStatement();
			String sql = "INSERT INTO ride (requestid, ridetype,pickuplocation,dropofflocation,pickuptime,noofseats,vehicletype)"
					+ "VALUES ("
					+ reqId
					+ ","
					+ "\""
					+ ridetype
					+ "\""
					+ ","
					+ "\""
					+ pickuploc
					+ "\""
					+ ","
					+ "\""
					+ dropoffloc
					+ "\""
					+ ","
					+ "\""
					+ pickuptime
					+ "\""
					+ ","
					+ String.valueOf(noofseats)
					+ ","
					+ "\""
					+ vehicletype
					+ "\"" + ")";
			ridestatement.executeUpdate(sql);

		} finally {
			DbUtil.close(ridestatement);
			DbUtil.close(rideconnection);
		}

	}

}
