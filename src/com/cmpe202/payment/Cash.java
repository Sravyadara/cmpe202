package com.cmpe202.payment;

import java.util.HashMap;

public class Cash extends Payment{

	@Override
	public int pay(int amount, HashMap<String, String> details) {
		// TODO Auto-generated method stub
		//return "Paying through Cash Mode.... " + notifyCustomer();
		return 0;
		
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
	
	public String notifyCustomer() {
		notifyDispatcher();
		return "Payment accepted. Hope you enjoyed our service. Have a good one.";
	}

	public String notifyDispatcher() {
		return "Notifying Dispatcher on Successfull Payment"; 
	}
}
