package com.cmpe202.ride;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.cmpe202.member.Member;
import com.cmpe202.payment.Cash;
import com.cmpe202.payment.Coupons;
import com.cmpe202.payment.CreditCard;
import com.cmpe202.payment.DebitCard;
import com.cmpe202.payment.Pass;
import com.cmpe202.payment.PayPal;
import com.cmpe202.payment.Payment;
import com.cmpe202.request.ConnectionFactory;
import com.cmpe202.request.DbUtil;

public class Ride extends Dispatch { 
	private Connection rideconnection;
    private Statement ridestatement;
    
    public Ride(Payment p) {
    	super(p);
    }
    
	@Override
	
    public int pay(int amount, HashMap<String, String> paymentModeDetails) {
		int paymentStatusCode = payByMode(amount, paymentModeDetails);
		return paymentStatusCode;
	}
    
	@Override
	public long calculateAmount() {
		// get time from calcutateTime() method and calculate distance here.
		// After that call pay method which is above this one by sending amount as argument to it.
		// That pay internally calls payByCalls().
		return 0;
	}
	public void insertRide(String memberId,String reqType)throws SQLException {
		  try{
			  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 	   //get current date time with Date()
		 	   Date date = new Date();
		 	   String reqdate= dateFormat.format(date).toString();
		 	   String stareq="Initiated";
	       rideconnection = ConnectionFactory.getConnection();
	       ridestatement = rideconnection.createStatement();
	       String sql = "INSERT INTO request (member_emailid, requesttype,request_state,date)" +
	                    "VALUES (" + "\""  + memberId + "\""  + "," + "\""  + reqType + "\""  + "," + "\""  +stareq + "\""  +","+ "\""  +reqdate + "\""  +")";
	       ridestatement.executeUpdate(sql);
	       System.out.println("Inserted into Request table");
		      
	
	}finally {
      DbUtil.close(ridestatement);
      DbUtil.close(rideconnection);
  }


}
	
	
}
