package com.cmpe202.payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.cmpe202.request.ConnectionFactory;
import com.cmpe202.request.DBQuery;
import com.mysql.jdbc.Connection;

public class PeakHourBill extends Payment {

	public boolean IsPeakHour() {
		return true;

	}

	@Override
	public int pay(int amount, HashMap<String, String> details) {
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public int calculateTotalAmount(int amount) {
		// TODO Auto-generated method stub

		int totalAmount = amount + 60;

		return totalAmount;
	}

	@Override
	public void notifyUserAboutAdditionalCharges() {
		// TODO Auto-generated method stub

		System.out.println("Additional charges applied due to peak hour ride");

	}

}
