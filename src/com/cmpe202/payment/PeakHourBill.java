
package com.cmpe202.payment;

import java.util.HashMap;

public class PeakHourBill extends Payment{
	
	public boolean IsPeakHour(){
		return true;
	}

	@Override
	public int pay(int amount, HashMap<String, String> details) {
		// TODO Auto-generated method stub
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

}
