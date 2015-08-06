package com.cmpe202.request;

import java.util.Date;

import com.cmpe202.payment.Payment;

public abstract class IssuePass {
	
	protected Payment p;
	
	public abstract void pay();
	
	public IssuePass(Payment p){
		this.p = p;
		
	}
	protected void payByCC(int amount, int noOfDays, int creditCardNumber, int CVV , String NameOnCard, Date expirydate){
		
	}
	protected void payByDC(int amount, int noOfDays, int debitCardNumber, int CVV , String NameOnCard, Date expirydate){
		
	}
	protected void payByCoupon(int amount, int noOfDays, int couponNo){
		
	}
	protected void payByCash(int amount, int noOfDays){
		
	}
	protected void paywithPaypal(int amount, int noOfDays, String paypalAccountId){
		
	}
	public static void main(String[] args)
	{
		System.out.println("*******************PASS***********************");
	}

}
