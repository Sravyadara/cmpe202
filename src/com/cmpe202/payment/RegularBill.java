package com.cmpe202.payment;

import java.util.HashMap;

public class RegularBill extends Payment{

	@Override
	public String pay(int amount, HashMap<String, String> details) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateTotalAmount(int amount) {
		// TODO Auto-generated method stub
		return amount + 0;
	}

	@Override
	public void notifyUserAboutAdditionalCharges() {
		
		// TODO Auto-generated method stub
		System.out.println("No additional charges applied");
	}
	public boolean IsPeakHour(){
		return true;
	}


}
