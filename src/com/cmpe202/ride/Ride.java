package com.cmpe202.ride;

import java.util.Date;

import com.cmpe202.payment.Payment;
import com.cmpe202.request.Request;

public abstract class Ride {

	protected Payment p;
	private Request request;
    private Payment payment;
    private RideStateInterface rideState;
	
<<<<<<< HEAD
	
	//  do we need to have a constructor here for Ride ?
	
	public abstract void pay(int amount);
	
	public abstract long calculateDistance();
	
	protected void payByCC(int amount, int noOfDays, int creditCardNumber, int CVV , String NameOnCard, Date expirydate){
		// It has to receive payment object. Check constructor above.
		// Call paymnetObject.pay();
		
	}
	protected void payByDC(int amount, int noOfDays, int debitCardNumber, int CVV , String NameOnCard, Date expirydate){
		// It has to receive payment object.
		// Call paymnetObject.pay();
	}
	protected void redeemCoupon(int amount, int noOfDays, int couponNo){
		// It has to receive payment object.
		// Call paymnetObject.pay();
	}
	protected void payByCash(int amount, int noOfDays){
		// It has to receive payment object.
		// Call paymnetObject.pay();
	}
	protected void paywithPaypal(int amount, int noOfDays, String paypalAccountId){
		// It has to receive payment object.
		// Call paymnetObject.pay();
	}
	
	
}
