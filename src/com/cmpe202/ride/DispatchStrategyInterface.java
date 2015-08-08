package com.cmpe202.ride;

import java.sql.SQLException;

import com.cmpe202.member.Driver;
import com.cmpe202.member.Member;

public interface DispatchStrategyInterface {

	public Driver searchDriver(Ride ride) throws SQLException;

}