package com.cmpe202.rules;

public class PaymentDecoratorRules extends DecoratorRules {
	private int taxiPrice = 1;
	private int ridesharePrice = 2;

	public int getRidesharePrice() {
		return ridesharePrice;
	}

	public int getTaxiPrice() {
		return ridesharePrice;
	}

	public void setRidesharePrice(int ridesharePrice) {
		this.ridesharePrice = ridesharePrice;
	}

	public void setTaxiPrice(int taxiPrice) {
		this.taxiPrice = taxiPrice;
	}

	public PaymentDecoratorRules(Rules rules) {
		super(rules);
		// TODO Auto-generated constructor stub
	}

	public String addRules() {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteRules() {
		// TODO Auto-generated method stub
		return null;
	}

}