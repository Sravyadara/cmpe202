package com.cmpe202.rules;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cmpe202.payment.Coupon;
import com.cmpe202.ride.DispatchDAO;

public class CouponRules extends Rules {

	private String couponId;
	private String expiry;

	public boolean validate() {

		boolean validationStatus = false;

		try {
			if (isValid(couponId)) {
				validationStatus = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return validationStatus;
	}

	public CouponRules(String couponId) {
		this.couponId = couponId;
	}

	public CouponRules() {

	}

	public boolean isValid(String couponId) throws SQLException, ParseException {

		boolean isvalid = false;
		DispatchDAO dispatchDAO = new DispatchDAO();
		Coupon coupon = dispatchDAO.getCoupon(couponId);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(expiry);
		Date date2 = new Date();

		if (coupon != null) {
			if (date1.before(date2)) {
				isvalid = true;
			}
		}
		return isvalid;
	}
}