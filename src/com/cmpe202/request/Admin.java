package com.cmpe202.request;

import java.sql.SQLException;
import java.util.Scanner;

import com.cmpe202.member.MemberDAO;
import com.cmpe202.payment.Coupon;
import com.cmpe202.payment.CouponDAO;
import com.cmpe202.ride.Vehicle;

public class Admin {

	static Scanner in = new Scanner(System.in);

	private static void displayCoupon(Coupon coupon) {
		System.out.println("Coupon ID:" + coupon.getCouponId());
		System.out.println("Coupon Validity:" + coupon.getCouponExpiry());
		System.out.println("Coupon Discount:" + coupon.getDiscount());
		System.out.println();
	}

	private static void displayVehicle(Vehicle vehicle) {
		System.out.println("Vehicle ID:" + vehicle.getVehicleId());
		System.out.println("Vehicle Seats:" + vehicle.getNoOfSeats());
		System.out.println("Vehicle Model:" + vehicle.getVehicle_model());
		System.out.println("Vehicle Type:" + vehicle.getVehicleType());
		System.out
				.println("Vehicle Specification:" + vehicle.getVehicle_spec());
		System.out.println();
	}

	public static void callVehicleMenu() throws Exception {
		MemberDAO memberDAO = new MemberDAO();
		String executivemenuselection;
		System.out
				.print("**********************************************************\n");
		System.out
				.print("                    Actions                               \n");
		System.out
				.print("**********************************************************\n");
		System.out.println("1. Add Vehicle");
		System.out.println("2. Delete Vehicle");
		System.out.println("3. Update Vehicle");
		System.out.print("Choose menu item: ");
		executivemenuselection = in.nextLine();
		if (executivemenuselection.equals("1")) {
			System.out.println("Enter Number of Seats");
			String noOfSeats = in.nextLine();
			System.out.println("Enter Vehicle Model");
			String vehicle_model = in.nextLine();
			System.out.println("Enter Vehicle Type");
			String vehicle_type = in.nextLine();
			System.out.println("Enter Vehicle Specification");
			String vehicle_spec = in.nextLine();

			Vehicle vehicle = new Vehicle();

			vehicle.setNoOfSeats(Integer.parseInt(noOfSeats));
			vehicle.setVehicle_model(vehicle_model);
			vehicle.setVehicleType(vehicle_type);
			vehicle.setVehicle_spec(vehicle_spec);

			int status = memberDAO.AddNewVehicle(vehicle);
			if (status == 1) {
				System.out.println("Vehicle Successfully added");
			}
			callAdminscreen();

		} else if (executivemenuselection.equals("2")) {

			System.out.println("Enter VehicleId");
			int vehicleId = in.nextInt();

			int status = memberDAO.removeVehicle(vehicleId);
			if (status == 1) {
				System.out.println("Vehicle Successfully deleted");
			}
			callAdminscreen();

		} else if (executivemenuselection.equals("3")) {

			System.out.println("Enter VehicleId");
			String vehicleId = in.nextLine();
			System.out.println("Enter Number of Seats");
			String noOfSeats = in.nextLine();
			System.out.println("Enter Vehicle Model");
			String vehicle_model = in.nextLine();
			System.out.println("Enter Vehicle Type");
			String vehicle_type = in.nextLine();
			System.out.println("Enter Vehicle Specification");
			String vehicle_spec = in.nextLine();

			Vehicle vehicle = new Vehicle();

			vehicle.setVehicleId(Integer.parseInt(vehicleId));
			vehicle.setNoOfSeats(Integer.parseInt(noOfSeats));
			vehicle.setVehicle_model(vehicle_model);
			vehicle.setVehicleType(vehicle_type);
			vehicle.setVehicle_spec(vehicle_spec);

			int status = memberDAO.UpdateVehicle(vehicle);
			if (status == 1) {
				System.out.println("Vehicle Successfully updated");
			}
			callAdminscreen();

		}
	}

	public static void callRulesMenu() throws Exception {
		CouponDAO couponDAO = new CouponDAO();
		String executivemenuselection;

		System.out
				.print("**********************************************************\n");
		System.out
				.print("                        Actions                           \n");
		System.out
				.print("**********************************************************\n");
		System.out.println("1. Add Coupon");
		System.out.println("2. Delete Coupon");
		System.out.println("3. Update Coupon");
		System.out.print("Choose menu item: ");
		executivemenuselection = in.nextLine();

		if (executivemenuselection.equals("1")) {

			System.out.println("Enter Coupon Validity in YYYY-MM-DD format\n");
			String expiry = in.nextLine();
			System.out.println("Enter Discount Percentage");
			String discount = in.nextLine();

			Coupon addCoupon = new Coupon();
			addCoupon.setCouponExpiry(expiry);
			addCoupon.setDiscount(discount);

			int status = couponDAO.AddCoupon(addCoupon);
			if (status == 1) {
				System.out.println("Coupon Successfully added");
			} else {
				System.out.println("Error while adding coupon");
			}
			callAdminscreen();

		} else if (executivemenuselection.equals("2")) {
			System.out.println("Enter Coupon Id");
			int couponId = in.nextInt();

			int status = couponDAO.removeCoupon(couponId);
			if (status == 1) {
				System.out.println("Coupon Successfully deleted");
			} else {
				System.out.println("Coupon does not exist");
			}
			callAdminscreen();

		} else if (executivemenuselection.equals("3")) {
			System.out.println("Enter Coupon Id");
			String couponId = in.nextLine();
			System.out.println("Enter Coupon Validity in YYYY-MM-DD format");
			String expiry = in.nextLine();
			System.out.println("Enter Discount Percentage");
			String discount = in.nextLine();

			Coupon updateCoupon = new Coupon();
			updateCoupon.setCouponId(couponId);
			updateCoupon.setCouponExpiry(expiry);
			updateCoupon.setDiscount(discount);

			int status = couponDAO.UpdateCoupon(updateCoupon);
			if (status == 1) {
				System.out.println("Coupon Successfully updated");
			} else {
				System.out.println("Error while updating coupon");
			}
			callAdminscreen();

		}
	}

	public static void main(String[] args) throws Exception {

		callAdminscreen();

	}

	public static void callAdminscreen() throws Exception {
		// Scanner in = new Scanner(System.in);
		String homescreenselection;
		System.out
				.print("**********************************************************\n");
		System.out
				.print("                        Actions                           \n");
		System.out
				.print("**********************************************************\n");
		System.out.println("1. Vehicle");
		System.out.println("2. Coupon");
		System.out.print("Choose menu item: ");
		homescreenselection = in.nextLine();
		if (homescreenselection.equals("1")) {
			callVehicleMenu();
		} else if (homescreenselection.equals("2")) {
			callRulesMenu();
		}
	}
}