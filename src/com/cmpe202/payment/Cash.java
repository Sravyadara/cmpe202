package com.cmpe202.payment;

import java.util.HashMap;

public class Cash extends Payment{

	@Override
	public int pay(int amount, HashMap<String, String> details) {
		String paymentMessage = "";
		// TODO Auto-generated method stub
		//return "Paying through Cash Mode.... " + notifyCustomer();
		System.out.println("Cash payment accepted.");
		System.out.println(notifyCustomer());
		return 1;
		
	}

	@Override
	public int calculateTotalAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notifyUserAboutAdditionalCharges() {
		// TODO Auto-generated method stub
		
	}
	
}
