package com.cmpe202.member;

public class Member {
	
	protected int memberid;
	protected String role;
	protected String address;
	protected String paymentDetails;
	protected String licenseDetails;
	protected int contact;
	protected String memberName;
	
	
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public String getLicenseDetails() {
		return licenseDetails;
	}
	public void setLicenseDetails(String licenseDetails) {
		this.licenseDetails = licenseDetails;
	}
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
}
