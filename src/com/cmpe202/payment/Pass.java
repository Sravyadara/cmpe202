package com.cmpe202.payment;

import java.util.HashMap;

public class Pass extends Payment {

	@Override
	public int pay(int amount, HashMap<String, String> details) {
		// TODO Auto-generated method stub
		// return "Paying through Weekly/Monthly Pass";
		String paymentMessage = "";
		int paymentStatusCode = 0;
		System.out.println("Paying through Pass");
		if (verifyPass()) {
			System.out.println("Pass Verification Done...");
			callSleep(5000);
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
			paymentMessage = "Invalid Pass. Please check and try again";
			paymentStatusCode = 3;
			System.out.println("Invalid Pass. Please check and try again");
		}
		// TODO Auto-generated method stub
		return paymentStatusCode;

	}

	public boolean verifyPass() {
		// Cross verify the card details with the values stored in database and
		// send corresponding boolean value.
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
