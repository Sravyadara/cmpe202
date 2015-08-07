package com.cmpe202.ride;

import java.util.HashMap;

import com.cmpe202.member.Member;
import com.cmpe202.payment.Payment;

public class Ride extends Dispatch { 
	
	public Ride(){}
	
	 public Ride(Payment p) {
		 super(p);
	 }
	
	@Override
	public void pay(int amount, HashMap<String, String> paymentModeDetails) {
		payByMode(amount, paymentModeDetails);
		
	}

	@Override
	public long calculateAmount() {
		// get time from calcutateTime() method and calculate distance here.
		// After that call pay method which is above this one by sending amount as argument to it.
		// That pay internally calls payByCalls().
		
		return 0;
	}	
	
	
}
