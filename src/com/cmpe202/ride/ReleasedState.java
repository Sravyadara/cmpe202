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
import com.cmpe202.rules.CouponDecoratorRules;
import com.cmpe202.rules.CouponRules;
import com.cmpe202.rules.DecoratorRules;
import com.cmpe202.rules.PaymentDecoratorRules;
import com.cmpe202.rules.PaymentRules;
import com.cmpe202.rules.Rules;

public class ReleasedState implements RideStateInterface {

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

	public ReleasedState(Dispatch d) {
		dispatchStateContext = d;
	}

	@Override
	public void initiateRide() {
		// TODO Auto-generated method stub
		System.out.println("Ride is already initiated");

	}

	@Override
	public int RideInTransit(HashMap<String, String> rideDetails) {
		// TODO Auto-generated method stub
		System.out.println("Ride is already in Transit");
		return 0;

	}

	@Override
	public int concludeRide(HashMap<String, String> rideDetails) {
		// TODO Auto-generated method stub
		// Display Start Button
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the option to stop the ride");
		System.out.println("1.Stop the Ride");
		int menuItem = in.nextInt();
		if (menuItem == 1) {
			Calendar cal = Calendar.getInstance();

			// time in milli seconds
			endTime = (int) cal.getTimeInMillis();
			dispatchStateContext.setRideEndTime(endTime);

			// time in hh:mm
			final String eTime = new SimpleDateFormat("HH:mm").format(cal
					.getTime());
			DBQuery rideDAO = new DBQuery();
			try {
				rideDAO.updateEndTime(eTime,
						Integer.parseInt(rideDetails.get("requestId")));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				/*rideDAO.updateRideStatus(dispatchStateContext,
						Integer.parseInt(rideDetails.get("requestId")));*/
				/*System.out.println("------------------------------------");
				System.out.println("Printing calculateTotalTime value : " + calculateTotalTime());*/
				rideDetails.put("rideDistance",
						Double.toString((calculateTotalTime())/1000.0));
				/*rideDetails.put("rideDistance",
						Integer.toString((calculateTotalTime())/1000));*/
				int rideAmount = calculateRideAmount(calculateAmount(rideDetails));
				rideDetails.put("rideAmount", Integer.toString(rideAmount));

				showPaymentOptions(rideDetails);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return endTime;

	}

	public int calculateTotalTime() {

		int totalTime = (int) (dispatchStateContext.getRideEndTime() - dispatchStateContext
				.getRideStartTime());
		dispatchStateContext.setRideTimeTaken(totalTime);
		return totalTime;
	}

	public int calculateAmount(HashMap<String, String> rideDetails) {

		int amount = (int) (((dispatchStateContext.getRideTimeTaken() / 1000) / 60) % 60);
		PaymentDecoratorRules pdr = new PaymentDecoratorRules(new PaymentRules());
		if(rideDetails.get("requestType").equalsIgnoreCase("Ride Share")) {
			amount = amount * pdr.getRidesharePrice();
		}else {
			amount = amount * pdr.getTaxiPrice();
		}
		//amount = amount * 2;
		dispatchStateContext.setBillGenerated(amount);
		/*System.out.println("Printing amount value in calculateAmount() : " +amount);*/
		return amount;

	}

	public int calculateRideAmount(int amount) {

		Payment p2 = new RegularBill();

		try {

			if (p2.isPeakHour() == true) {
				p2 = new PeakHourBill();
				totalRideAmount = p2.calculateTotalAmount(amount);
			} else
				totalRideAmount = p2.calculateTotalAmount(amount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalRideAmount;

	}

	public void showPaymentOptions(HashMap<String, String> rideDetails)
			throws SQLException {

		HashMap<String, String> paymentDetails = new HashMap<String, String>();
		int amount = Integer.parseInt(rideDetails.get("rideAmount"));
		String driverId = rideDetails.get("driverId");
		String memberId = rideDetails.get("memberId");
		int requestId = Integer.parseInt(rideDetails.get("requestId"));
		double rideDistance = Double.parseDouble(rideDetails
				.get("rideDistance"));
		System.out.println("Total Ride Distance : " + rideDistance + " miles");
		System.out.println("Total Ride Amount : $" + amount);
		System.out
				.println("\n=========================================================\n");
		System.out.println("Please choose from the below payment methods.");
		System.out.print("1. Pay Using Registered Card " + "\n"
				+ "2. Use Another Credit Card" + "\n" + "3. Debit Card" + "\n"
				+ "4. Paypal Account" + "\n" + "5. Pass" + "\n" + "6. Cash \n");
		
		System.out.println("***********************************************************");
		CouponDecoratorRules cdr = new CouponDecoratorRules(new CouponRules());
		int seasonalDiscount = cdr.checkseasonalCoupons();
		if (seasonalDiscount != 0) {
			System.out.println("Note : Labour day special discount of "
					+ seasonalDiscount
					+ "% applies by default to your entire ride amount");
		}
		System.out.println("***********************************************************");
		
		System.out.println("\nChoose menu item : ");
		in = new Scanner(System.in);
		String menuItemString = in.nextLine();
		int menuItem = Integer.parseInt(menuItemString);
		int paymentStatusCode, amountAfterDiscount;

		
		switch (menuItem) {
		case 1:
			System.out.println("Paying using registered credit card..\n");
			amountAfterDiscount = (int) checkForCoupon(amount, seasonalDiscount);
			p = new CreditCard();
			d1 = new Ride(p);
			// fetch details from DB and construct a hashmap.
			paymentDetails = getMemberCardDetails(memberId);
			paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
			checkPaymentStatusCode("credit card", paymentStatusCode, memberId,
					driverId, requestId, amountAfterDiscount, rideDistance,
					paymentDetails.get("cardNumber"), rideDetails);
			break;

		case 2:
			System.out.println("Paying using credit card..\n");
			amountAfterDiscount = (int)checkForCoupon(amount, seasonalDiscount);
			System.out.println("Please enter credit card number : ");
			creditOrDebitCommonCode();
			p = new CreditCard();
			d1 = new Ride(p);
			paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
			checkPaymentStatusCode("credit card", paymentStatusCode, memberId,
					driverId, requestId, amountAfterDiscount, rideDistance,
					cardNumber, rideDetails);

			break;

		case 3:
			System.out.println("Paying using debit card..\n");
			amountAfterDiscount = (int)checkForCoupon(amount, seasonalDiscount);
			System.out.println("Please enter debit card number : ");
			creditOrDebitCommonCode();
			p = new DebitCard();
			d1 = new Ride(p);
			paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
			checkPaymentStatusCode("debit card", paymentStatusCode, memberId,
					driverId, requestId, amountAfterDiscount, rideDistance,
					cardNumber,rideDetails);

			break;

		case 4:
			System.out.println("Paying using PayPal Account..\n");
			amountAfterDiscount = (int)checkForCoupon(amount, seasonalDiscount);
			System.out.println("Redirecting to Paypal website ");
			System.out.print("Enter paypal account id : ");
			paypalAccountId = in.nextLine();
			paymentDetails.put("paypalAccountId", paypalAccountId);
			p = new PayPal();
			d1 = new Ride(p);
			paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
			checkPaymentStatusCode("paypal", paymentStatusCode, memberId,
					driverId, requestId, amountAfterDiscount, rideDistance,
					paypalAccountId,rideDetails);
			break;

		case 5:
			System.out.println("Paying using existing Pass..\n");
			amountAfterDiscount = (int)checkForCoupon(amount, seasonalDiscount);
			System.out.println("Please enter weekly/monthly pass id : ");
			passId = in.nextLine();
			paymentDetails.put("passId", passId);
			p = new Pass();
			// payByPass(amount, passId);
			d1 = new Ride(p);
			paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
			checkPaymentStatusCode("pass", paymentStatusCode, memberId,
					driverId, requestId, amountAfterDiscount, rideDistance,
					passId, rideDetails);
			break;

		case 6:
			System.out.println("Paying using Cash..\n");
			amountAfterDiscount = (int) checkForCoupon(amount, seasonalDiscount);
			p = new Cash();
			// payByCash(amount);
			d1 = new Ride(p);
			paymentStatusCode = d1.pay(amountAfterDiscount, paymentDetails);
			checkPaymentStatusCode("cash", paymentStatusCode, memberId,
					driverId, requestId, amountAfterDiscount, rideDistance,
					"null", rideDetails);
			break;

		}

	}

	public double checkForCoupon(int amount, int sdiscount) throws SQLException {
		int discount = 0;
		double finalAmount = amount;
		System.out.println("Do you have any valid coupon ? (y/n) :");
		String customerResponse = in.nextLine();
		System.out.println("Customer response : " + customerResponse);
		if (customerResponse.equals("y")) {
			System.out.println("Enter Coupon Number : ");
			String couponNumber = in.nextLine();
			Rules couponRule = new CouponRules(couponNumber);
			if (couponRule.validate()) {
				DispatchDAO dao = new DispatchDAO();
				discount = Integer.parseInt(dao.getCoupon(couponNumber)
						.getDiscount());
				finalAmount = amount - ((discount / 100.0) * amount);
				System.out.println("Successfully applied your" + discount
						+ "% coupon");

			} else {
				finalAmount = amount;
				System.out.println("Sorry, the coupon you have entered is Invalid or explired.");
			}

			
		}
		if (sdiscount != 0) {

			finalAmount = finalAmount - ((sdiscount / 100.0) * finalAmount);
			System.out.println("Successfully applied seasonal " + sdiscount
					+ "% coupon");
		}
		System.out.println("\nTotal amount charged : $" + finalAmount);
		return finalAmount;
	}

	public void creditOrDebitCommonCode() {
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

	public void checkPaymentStatusCode(String mode, int code, String memberId,
			String driverId, int requestId, int amount, double distance,
			String card, HashMap<String,String> rideDetails) throws SQLException {
		if (code == 1) {

			// Update database and Call or present main menu
			try {
				insertTransactionRecord(memberId,amount,distance,mode,card,"success",driverId,requestId);
				// insertTransactionRecord("ramyaky@gmail.com", 20, 30,
				// "credit card", "48674896506", "success",
				// "dummydriver@gmail.com", 12345);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		 else if(code == 2 || code == 3) { showPaymentOptions(rideDetails); }
		 
	}

	public void insertTransactionRecord(String memberId, int amount,
			double distance, String mode, String card, String status,
			String driverId, int requestId) throws SQLException {
		try {

			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			String sql = "INSERT INTO payment (payment_member_emailid, payment_requestid, payment_driver_emailid, distance_travelled, amount_charged, payment_mode, paymentcard, paymentstatus )"
					+ "VALUES (" + "\""
					+ memberId
					+ "\""
					+ ","
					+ "\""
					+ requestId
					+ "\""
					+ ","
					+ "\""
					+ driverId
					+ "\""
					+ ","
					+ "\""
					+ distance
					+ "\""
					+ ","
					+ "\""
					+ amount
					+ "\""
					+ ","
					+ "\""
					+ mode
					+ "\""
					+ ","
					+ "\""
					+ card
					+ "\""
					+ ","
					+ "\""
					+ status
					+ "\""
					+ ")";
			statement.executeUpdate(sql);
			/*System.out
					.println("Successfully inserted transaction status record");*/

		} finally {
			DbUtil.close(statement);
			DbUtil.close(connection);
		}

	}

	public HashMap<String, String> getMemberCardDetails(String memberId)
			throws SQLException {
		HashMap<String, String> hm = new HashMap<String, String>();
		try {
			ResultSet resultSet;

			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			String sql = "SELECT paymentcard, name, cvv, expirydate FROM member where member_emailid = "
					+ "\"" + memberId + "\"";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				hm.put("cardNumber", resultSet.getString("paymentcard"));
				hm.put("cardHolderName", resultSet.getString("name"));
				hm.put("cvv", resultSet.getString("cvv"));
				hm.put("expiry", resultSet.getString("expirydate"));
			}

		} finally {
			DbUtil.close(statement);
			DbUtil.close(connection);
		}

		return hm;
	}

}
