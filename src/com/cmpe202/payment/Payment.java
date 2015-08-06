package com.cmpe202.payment;

import java.util.HashMap;

public abstract class Payment {

	abstract public String pay(int amount, HashMap<String, String> details);
	
	public void calculateBill(){
		if (isPeakHour()){
			notifyUserAboutAdditionalCharges();
		}
		calculateTotalAmount();
	}
	public abstract int calculateTotalAmount();
	boolean isPeakHour(){
		return true;
	}
	public abstract void notifyUserAboutAdditionalCharges();	
	}
