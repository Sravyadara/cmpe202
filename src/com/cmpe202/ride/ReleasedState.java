package com.cmpe202.ride;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.cmpe202.payment.Cash;
import com.cmpe202.payment.Coupons;
import com.cmpe202.payment.CreditCard;
import com.cmpe202.payment.DebitCard;
import com.cmpe202.payment.Pass;
import com.cmpe202.payment.PayPal;
import com.cmpe202.payment.Payment;
import com.cmpe202.payment.PeakHourBill;
import com.cmpe202.payment.RegularBill;
import com.cmpe202.request.ConnectionFactory;
import com.cmpe202.request.DBQuery;
import com.cmpe202.request.DbUtil;
import com.cmpe202.rules.CouponRules;
import com.cmpe202.rules.Rules;

public class ReleasedState implements RideStateInterface{
	
	private Dispatch dispatchStateContext;
	private int endTime;
	private int totalRideAmount;
	Dispatch d1;
	Payment p;
	String cardNumber, cvv;
	String cardHolderName, expiry, paypalAccountId, passId, couponId;
	Scanner in;
	 HashMap<String, String> paymentDetails = new HashMap<String, String>();
	private Connection connection;
    private Statement statement;
	
	
	public ReleasedState(Dispatch d){
		dispatchStateContext = d;
	}

	@Override
	public void initiateRide() {
		// TODO Auto-generated method stub
		System.out.println("Ride is already initiated");
		
	}

	@Override
	public int RideInTransit() {
		// TODO Auto-generated method stub
		System.out.println("Ride is already in Transit");
		return 0;
		
	}

	@Override
	public int concludeRide() {
		// TODO Auto-generated method stub
		//Display Start Button
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the option to start the ride");
		System.out.println("1.Stop the Ride");
		int menuItem = in.nextInt();
		if(menuItem == 1){
		Calendar cal = Calendar.getInstance();
		
		//time in milli seconds
		  endTime = (int) cal.getTimeInMillis();
		  dispatchStateContext.setRideEndTime(endTime);
		  
		  //time in hh:mm
		  final String eTime =
		    	    new SimpleDateFormat("HH:mm").format(cal.getTime());
		 DBQuery rideDAO = new DBQuery();
		 try {
			rideDAO.updateEndTime(eTime, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 try {
			rideDAO.updateRideStatus(dispatchStateContext, 1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		}
	
		return endTime;
        
		
			
	}
	
	public void calculateTotalTime(){
		
		int totalTime = (int) (dispatchStateContext.getRideEndTime() - dispatchStateContext.getRideEndTime());
		dispatchStateContext.setRideTimeTaken(totalTime);
	}
	
	public int calculateAmount(){
		
		int amount = (int) (((dispatchStateContext.getRideTimeTaken()/1000)/60)%60);
		amount = amount * 2;
		dispatchStateContext.setBillGenerated(amount);
		return amount;		
		
	}
	
    public void calculateRideAmount(int amount) {
		
		Payment p2 = new RegularBill();
		
		 try {
			
			if(p2.isPeakHour() == true ){
				  p2 = new PeakHourBill();
				   totalRideAmount = p2.calculateTotalAmount(amount);
			 }else
				   totalRideAmount = p2.calculateTotalAmount(amount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 showPaymentOptions(1,totalRideAmount);
		/* * Payment p2 = new PeakHorBill()
		 * total = p2.calculateBill(amount)
		 * if false the create RegularBill
		 * 
		showPaymentOptions(reid/ memid , total)*/
	
	}
	 
		 
	
	public void showPaymentOptions(int memberId, int amount) {
	/* public void calculateRideAmount(int amount) {
		
		Payment p2 = new RegularBill();
		 p2.isPeakHour() id it is true then create an object for PeakHourBill
		 * Payment p2 = new PeakHorBill()
		 * total = p2.calculateBill(amount)
		 * if false the create RegularBill
		 * 
		showPaymentOptions(reid/ memid , total)
	} */
	}
		
	public void showPaymentOptions(String memberId, int amount) throws SQLException {
		
		HashMap<String, String> paymentDetails = new HashMap<String, String>();
		System.out.println("Please choose from the below payment methods.");
		System.out.print("1. Pay Using Registered Card " + "\n" + "2. Use Another Credit Card" + "\n" + "3. Debit Card" + "\n" + "4. Paypal Account" + "\n" + "5. Pass" + "\n" + "6. Coupons" + "\n" + "7. Cash \n");
		System.out.println("Choose menu item : ");
		in = new Scanner(System.in);
		String menuItemString = in.nextLine();
		int menuItem = Integer.parseInt(menuItemString);
		int paymentStatusCode, amountAfterDiscount;
				
		switch(menuItem) {
			case 1:
				System.out.println("Paying using registered credit card..");
				amountAfterDiscount = checkForCoupon(amount);
				p = new CreditCard();
				d1 = new Ride(p);
				// fetch details from DB and construct a hashmap.				
				paymentDetails = getMemberCardDetails(memberId);
				paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
				checkPaymentStatusCode("credit card", paymentStatusCode, memberId, amountAfterDiscount, paymentDetails.get("cardNumber"));
				break;
				
			case 2:
				System.out.println("Paying using credit card..");
				amountAfterDiscount = checkForCoupon(amount);
				System.out.println("Please enter credit card number : ");				
				creditOrDebitCommonCode();					
				p = new CreditCard();				
				d1 = new Ride(p);
				paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
				checkPaymentStatusCode("credit card", paymentStatusCode, memberId, amountAfterDiscount, cardNumber);
				
				break;
			
			case 3:
				System.out.println("Paying using debit card..");
				amountAfterDiscount = checkForCoupon(amount);
				System.out.println("Please enter debit card number : ");				
				creditOrDebitCommonCode();					
				p = new DebitCard();	
				d1 = new Ride(p);
				paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
				checkPaymentStatusCode("debit card", paymentStatusCode, memberId, amountAfterDiscount, cardNumber);
				
				break;
				
			case 4:
				System.out.println("Paying using PayPal Account..");
				amountAfterDiscount = checkForCoupon(amount);
				System.out.println("Redirecting to Paypal website ");				
				System.out.print("Enter paypal account id : ");
				paypalAccountId = in.nextLine();
				paymentDetails.put("paypalAccountId", paypalAccountId);
				p = new PayPal();				
				d1 = new Ride(p);
				paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
				checkPaymentStatusCode("paypal",paymentStatusCode, memberId, amountAfterDiscount, paypalAccountId);
				break;
				
			case 5:
				System.out.println("Paying using existing Pass..");
				amountAfterDiscount = checkForCoupon(amount);
				System.out.println("Please enter weekly/monthly pass id : ");
				passId = in.nextLine();
				paymentDetails.put("passId", passId);
				p = new Pass();
				//payByPass(amount, passId);
				d1 = new Ride(p);
				paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
				checkPaymentStatusCode("pass", paymentStatusCode, memberId, amountAfterDiscount, passId);
				break;
			
			/*case 6:
				System.out.print("Please enter coupon number : ");
				couponId = in.nextLine();
				paymentDetails.put("couponId", couponId);
				p = new Coupons();
				//redeemCoupon(amount, couponId);
				d1 = new Ride(p);
				d1.pay(amount, paymentDetails);
				break;*/
				
			case 6:
				System.out.println("Paying using Cash..");
				amountAfterDiscount = checkForCoupon(amount);
				p = new Cash();
				//payByCash(amount);
				d1 = new Ride(p);
				paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
				checkPaymentStatusCode("cash", paymentStatusCode, memberId, amountAfterDiscount, "null");
				break;
				
		}
		
	}
	
	public int checkForCoupon(int amount) throws SQLException {
		int discount = 0;
		int finalAmount = 0;
		System.out.println("Do you have any valid coupon ? (y/n) :");
		String customerResponse = in.nextLine();
		System.out.println("Customer response : " + customerResponse);
		if(customerResponse.equals("y")) {
			System.out.println("Enter Coupon Number : ");
			String couponNumber = in.nextLine();
			System.out.println("Entered number : " + couponNumber);
			Rules couponRule = new CouponRules(couponNumber);
			if(couponRule.validate()) {
				DispatchDAO dao = new DispatchDAO();
				discount = Integer.parseInt(dao.getCoupon(couponNumber).getDiscount());
				finalAmount = amount - ((discount/100) * amount );
			}else {
				finalAmount = amount;
			}
		}
		
		return finalAmount;
	}
	
	public  void creditOrDebitCommonCode() {
		cardNumber = in.nextLine();
		paymentDetails.put("cardNumber", cardNumber);
		System.out.println("Enter cardholder name as shown on the card : ");
		cardHolderName = in.nextLine();
		paymentDetails.put("cardHolderName", cardHolderName);
		System.out.println("Enter expiry date : ");
		expiry = in.nextLine();
		paymentDetails.put("expiry", expiry);
		System.out.println("Enter CVV number : ");
		cvv = in.nextLine();
		paymentDetails.put("cvv", cvv);		
	}
	
	public  void checkPaymentStatusCode(String mode, int code, String memberId, int amount, String card) throws SQLException {
		if(code == 1) {
			
			// Update database and Call or present main menu
			try {
				//insertTransactionRecord("ramyaky@gmail.com", 20, 30, "credit card", "48674896506", "success", "dummydriver@gmail.com", 12345);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}else if(code == 2 || code == 3) {
			showPaymentOptions(memberId, amount);
		}
	}
	
	public  void insertTransactionRecord(String memberId, int amount, double distance, String mode, String card, String status, String driverId, int requestId)throws SQLException {
		  try{
			  
			  connection = ConnectionFactory.getConnection();
		      statement = connection.createStatement();
		      String sql = "INSERT INTO payment (payment_member_emailid, payment_requestid, payment_driver_emailid, distance_travelled, amount_charged, payment_mode, paymentcard, paymentstatus )" +
		    		  		"VALUES (" + "\""  + memberId + "\""  + "," + "\""  + requestId + "\""  + "," + "\""  + driverId + "\""  + "," + "\""  + distance + "\""  + "," + "\""  + amount + "\""  + "," + "\""  + mode + "\""  + "," + "\""  + card + "\""  + "," + "\""  + status + "\""  +")";
		      statement.executeUpdate(sql);
		      System.out.println("Successfully inserted transaction status record");
			  
		  }finally {
		        DbUtil.close(statement);
		        DbUtil.close(connection);
		    }
		
	}
	
	public  HashMap<String, String> getMemberCardDetails(String memberId) throws SQLException {
		HashMap<String, String> hm = new HashMap<String, String>();
		try{
			  ResultSet resultSet;
			  
			  connection = ConnectionFactory.getConnection();
		      statement = connection.createStatement();		      
		      String sql = "SELECT paymentcard, name, cvv, expirydate FROM member where member_emailid = " + "\"" + memberId + "\"";
		      resultSet = statement.executeQuery(sql);
		      while(resultSet.next()) {
		    	  hm.put("cardNumber", resultSet.getString("paymentcard"));
		    	  hm.put("cardHolderName", resultSet.getString("name"));
		    	  hm.put("cvv", resultSet.getString("cvv"));
		    	  hm.put("expiry", resultSet.getString("expirydate"));
		      }
			  
		  }finally {
		        DbUtil.close(statement);
		        DbUtil.close(connection);
		    }
		
		return hm;
	}

}
