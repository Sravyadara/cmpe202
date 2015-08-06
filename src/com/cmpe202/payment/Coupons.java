package com.cmpe202.payment;

public class Coupons extends Payment{

	@Override
	public String pay(int amount, String details) {
		System.out.println("Please hold on for a second, applying coupons..");		
		String paymentMessage = "";
		if(verifyCoupon()) {
			int couponValue = getCouponValue();
			if(couponValue >= amount) {
				paymentMessage = "Your coupon value satisfies entire ride amount." + notifyCustomer();
			}
			else {
				int amountToBePaid = amount - couponValue;
				paymentMessage = "Successfully applied Coupon. The amount still need to be paid : " + amountToBePaid;
				// Still need to paid the remaining amount. write code for it.
			}
		}
		else {
			paymentMessage = "Invalid Coupon.";
		}
		// TODO Auto-generated method stub
		return paymentMessage;
	}

	public boolean verifyCoupon() {
		// Check against DB and return corresponding value
		return true;
	}
	
	public int getCouponValue() {
		// Get value from DB and send it
		return 10;
	}
	
	public String notifyCustomer() {
		notifyDispatcher();
		return "Payment accepted. Hope you enjoyed our service. Have a good one.";
	}

	public String notifyDispatcher() {
		return "Notifying Dispatcher on Successfull Payment"; 
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
