package com.cmpe202.payment;

public class Coupon {

	private String couponId;
	private String couponExpiry;
	private String discount;

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponExpiry() {
		return couponExpiry;
	}

	public void setCouponExpiry(String couponExpiry) {
		this.couponExpiry = couponExpiry;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

}