package com.cmpe202.rules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CouponDecoratorRules extends DecoratorRules {

	public CouponDecoratorRules(Rules rules) {
		super(rules);
	}

	public String addRules() {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteRules() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validate() {

		super.validate();
		return false;
	}

	public int checkseasonalCoupons() {

		int discountAmount = 0;
		Date todayDate = new Date();

		try {
			discountAmount = isWithinRange(todayDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return discountAmount;

	}

	int isWithinRange(Date testDate) throws ParseException {

		int discountAmount = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate1 = sdf.parse("2015-12-1");
		Date endDate1 = sdf.parse("2015-12-25");

		Date startDate2 = sdf.parse("2015-05-7");
		Date endDate2 = sdf.parse("2015-07-20");

		if (testDate.after(startDate2) || testDate.before(endDate2)) {
			discountAmount = 20;
		}
		if (testDate.after(startDate1) || testDate.before(endDate1)) {
			discountAmount = 10;
		}

		return discountAmount;
	}

}