package com.cmpe202.payment;

import java.util.HashMap;

public class PayPal extends Payment {

	@Override
	public int pay(int amount, HashMap<String, String> details) {
		String paymentMessage = "";
		int paymentStatusCode = 0;
		System.out.println("Paying through Paypal Account");
		callSleep(2000);
		if (verifyAccount()) {
			if (fwdToPaymentGateway()) {
				notifyCustomer();
				paymentStatusCode = 1;

			} else {
				paymentMessage = "Transaction is incomplete. Please try again.";
				paymentStatusCode = 2;
				System.out
						.println("Transaction is incomplete. Please try again.");
			}
		} else {
			callSleep(2000);
			paymentMessage = "Invalid Credentials. Please check and try again";
			paymentStatusCode = 3;
			System.out
					.println("Invalid credit card. Please check and try again");
		}
		// TODO Auto-generated method stub
		return paymentStatusCode;
	}

	public boolean verifyAccount() {
		// Cross verify the card details with the values stored in database and
		// send corrsponding boolean value.
		return true;
	}

	@Override
	public int calculateTotalAmount(int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notifyUserAboutAdditionalCharges() {
		// TODO Auto-generated method stub

	}

}
