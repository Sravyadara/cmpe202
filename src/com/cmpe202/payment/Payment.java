package com.cmpe202.payment;

import java.sql.SQLException;
import java.util.HashMap;

import com.cmpe202.notifications.Notifications;
import com.cmpe202.request.DBQuery;

public abstract class Payment {

	abstract public int pay(int amount, HashMap<String, String> details);

	Notifications notification = new Notifications();

	public void calculateBill(int amount) throws SQLException {
		if (isPeakHour()) {
			notifyUserAboutAdditionalCharges();
		}
		calculateTotalAmount(amount);
	}

	public abstract int calculateTotalAmount(int amount);

	public boolean isPeakHour() throws SQLException {
		DBQuery query = new DBQuery();
		boolean peakHourBill = false;
		if (query.requestCount() > query.driverCount())
			peakHourBill = true;
		return peakHourBill;
	}

	public abstract void notifyUserAboutAdditionalCharges();

	public void notifyCustomer() {
		notifyDispatcher();
		notification
				.sendMessage("\nNotifying Customer --> Payment accepted. Thanks for using our service. \n \n Hope you enjoyed our service. Have a good one\n.");
		;
	}

	public void notifyDispatcher() {
		notification.sendMessage(" ( ---- Notifying Dispatcher about Successful Payment ----");
	}

	public boolean fwdToPaymentGateway() {
		return true;
	}

	public void callSleep(int mseconds) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
