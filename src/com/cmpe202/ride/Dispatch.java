package com.cmpe202.ride;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import com.cmpe202.member.Driver;
import com.cmpe202.member.Member;
import com.cmpe202.payment.CreditCard;
import com.cmpe202.payment.Payment;
import com.cmpe202.request.Request;

public abstract class Dispatch {

	private Request request;
	private Payment payment;
	private RideStateInterface rideState;
	private DispatchStrategyInterface ds;
	private long rideStartTime;
	private long rideEndTime;
	private long rideTimeTaken;
	private int billGenerated;

	public int getBillGenerated() {
		return billGenerated;
	}

	public void setBillGenerated(int billGenerated) {
		this.billGenerated = billGenerated;
	}

	public long getRideStartTime() {
		return rideStartTime;
	}

	public void setRideStartTime(long rideStartTime) {
		this.rideStartTime = rideStartTime;
	}

	public long getRideEndTime() {
		return rideEndTime;
	}

	public void setRideEndTime(long rideEndTime) {
		this.rideEndTime = rideEndTime;
	}

	public long getRideTimeTaken() {
		return rideTimeTaken;
	}

	public void setRideTimeTaken(long rideTimeTaken) {
		this.rideTimeTaken = rideTimeTaken;
	}

	public Dispatch() {
		rideState = new WaitingState(this);
	}

	public Dispatch(Payment paymentType) {
		this.payment = paymentType;
	}

	public abstract int pay(int amount,
			HashMap<String, String> paymentModeDetails);

	public abstract long calculateAmount();

	protected int payByMode(int amount,
			HashMap<String, String> paymentModeDetails) {
		int paymentStatusCode = payment.pay(amount, paymentModeDetails);
		return paymentStatusCode;

	}

	public void notifyDriver() {

	}

	public Driver searchDriver(Ride ride) throws SQLException {
		ds = setStrategy(ride);
		return ds.searchDriver(ride);
	}

	public void notifyCustomer() {

	}

	public DispatchStrategyInterface setStrategy(Ride ride) {
		if (ride.getRidetype().equals("Taxi")) {
			ds = new TaxiDispatch();

		} else if (ride.getRidetype().equals("Ride Share")) {
			ds = new RideShareDispatch();
		}
		return ds;

	}

	public RideStateInterface getRideState() {
		return rideState;
	}

	public void setRideState(RideStateInterface rideState) {
		this.rideState = rideState;
	}

	public void initiateRide() {
		this.rideState.initiateRide();
	}

	public void RideInTransit(HashMap<String, String> rideDetails) {
		this.rideState.RideInTransit(rideDetails);

	}

	public void ConcludeRide(HashMap<String, String> rideDetails) {
		this.rideState.concludeRide(rideDetails);
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
