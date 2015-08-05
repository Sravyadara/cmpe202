package com.cmpe202.payment;

public abstract class Payment {

	abstract public String pay(int amount, String details);
	
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
