package com.cmpe202.request;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.sql.*;
import java.util.*;

import com.cmpe202.member.MemberDAO;
import com.cmpe202.member.Member;

public class Request {

	// private String requestType;
	RequestStateInterface requestState;
	protected String requestType;

	protected String request_status;

	// protected DateTime requestdate;

	public Request() {
		requestState = new InitiateRequest(this);
	}

	public void receiveRequest(String memberId, String requestType,
			String ridetype, String pickuploc, String dropoffloc,
			String vehicletype, String pickuptime, int noofseats) {
		System.out.println(requestState.receiveRequest(memberId, requestType,
				ridetype, pickuploc, dropoffloc, vehicletype, pickuptime,
				noofseats));
		System.out.println(requestState.processRequest(requestType));

	}

	public void receiveRequest(String memberId, String requestType) {
		System.out.println(requestState.receiveRequest(memberId, requestType));
		System.out.println(requestState.processRequest(requestType));

	}

	public void concludeRequest() {
		System.out.println(requestState.concludeRequest());
	}

	public void cancelRequest() {
		System.out.println(requestState.cancelRequest());
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public RequestStateInterface getRequestState() {
		return requestState;
	}

	public void setRequestState(RequestStateInterface requestState) {
		this.requestState = requestState;
	}

	public String getRequest_status() {
		return request_status;
	}

	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}

	public static void InsertRequest() {
		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// //get current date time with Date()
		// Date date = new Date();
		// System.out.println(dateFormat.format(date));
		// String reqdate= dateFormat.format(date).toString();
		// System.out.println(reqdate);
		// 9999-12-31 23:59:59
		// YYYY-MM-DD HH:MM:SS
		// get current date time with Calendar()
		// Calendar cal = Calendar.getInstance();
		// System.out.println(dateFormat.format(cal.getTime()));
	}

}