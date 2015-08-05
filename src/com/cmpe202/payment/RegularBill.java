package com.cmpe202.payment;

public class RegularBill extends Payment{

	@Override
	public String pay(int amount, String details) {
		// TODO Auto-generated method stub
		return null;
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
	public boolean IsPeakHour(){
		return true;
	}


}
