package com.cmpe202.payment;

import java.sql.SQLException;

import com.cmpe202.request.ConnectionFactory;

import com.cmpe202.request.DbUtil;

import com.mysql.jdbc.Connection;

import com.mysql.jdbc.Statement;

public class CouponDAO {

	private Connection connection;

	private Statement statement;

	public int AddCoupon(Coupon coupon) throws SQLException {

		String query = "INSERT INTO coupon(couponid,couponexpiry,discount) VALUES "

				+ "( "
				+ coupon.getCouponId()
				+ ",'"
				+ coupon.getCouponExpiry()
				+ "','" + coupon.getDiscount() + "')";

		int status = 0;

		try {

			connection = (Connection) ConnectionFactory.getConnection();

			statement = (Statement) connection.createStatement();

			status = statement.executeUpdate(query);

		} finally {

			DbUtil.close(statement);

			DbUtil.close(connection);

		}

		return status;

	}

	// to be used for admin to update Coupon

	public int UpdateCoupon(Coupon coupon) throws SQLException {

		String query = " UPDATE coupon SET discount='" + coupon.getDiscount()
				+ "',couponexpiry='" + coupon.getCouponExpiry()
				+ "' WHERE couponid=" + coupon.getCouponId();

		int status = 0;

		try {

			connection = (Connection) ConnectionFactory.getConnection();

			statement = (Statement) connection.createStatement();

			status = statement.executeUpdate(query);

		} finally {

			DbUtil.close(statement);

			DbUtil.close(connection);

		}

		return status;

	}

	// to be used for admin to remove coupon from inventory

	public int removeCoupon(int CouponId) throws SQLException {

		String query = "DELETE FROM coupon WHERE couponid=" + CouponId;

		int status = 0;

		try {

			connection = (Connection) ConnectionFactory.getConnection();

			statement = (Statement) connection.createStatement();

			status = statement.executeUpdate(query);

		} finally {

			DbUtil.close(statement);

			DbUtil.close(connection);

		}

		return status;

	}

}