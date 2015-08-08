package com.cmpe202.request;

import java.util.Date;
import java.util.Scanner;

import com.cmpe202.payment.Payment;

public class IssuePass {

	public static void issuePass() {

		System.out
				.println("*******************PASS SERVICE***********************");
		System.out.println("Enter your required option");
		System.out.println("1.Weekly Pass");
		System.out.println("2.Monthly Pass");
		Scanner in = new Scanner(System.in);
		int opt = in.nextInt();
		String passDate;
		String mode;
		boolean quit = false;

		switch (opt) {

		case 1: {
			Scanner newin = new Scanner(System.in);
			System.out
					.println("*******************Weekly Pass***********************");
			System.out
					.println("                                                     ");
			System.out.println("Enter the start date in mm/dd/yy");
			passDate = newin.nextLine();
			System.out.println("Please enter your payment mode:");
			System.out.println("1.Credit Card");
			System.out.println("2.Debit Card");
			System.out.println("3.PapPal");
			System.out.println("4.Coupon");
			mode = newin.nextLine();
			if (mode.equalsIgnoreCase("1")) {
				mode = "Credit Card";
			} else if (mode.equalsIgnoreCase("2")) {
				mode = "Debit Card";

			} else if (mode.equalsIgnoreCase("3")) {
				mode = "Paypal";

			} else if (mode.equalsIgnoreCase("4")) {
				mode = "Coupon";

			}

			System.out.println("Payment Processed using " + mode);
			System.out.println("Payment Processed successfully");
			System.out.println("Your pass will expire in 7 days..");

		}

			break;

		case 2: {
			Scanner newin = new Scanner(System.in);
			System.out
					.println("*******************Monthly Pass***********************");
			System.out
					.println("                                                     ");
			System.out.println("Enter the start date in mm/dd/yy");
			passDate = newin.nextLine();
			System.out.println("Please enter your payment mode:");
			System.out.println("1.Credit Card");
			System.out.println("2.Debit Card");
			System.out.println("3.PapPal");
			System.out.println("4.Coupon");
			mode = newin.nextLine();
			if (mode.equalsIgnoreCase("1")) {
				mode = "Credit Card";
			} else if (mode.equalsIgnoreCase("2")) {
				mode = "Debit Card";

			} else if (mode.equalsIgnoreCase("3")) {
				mode = "Paypal";

			} else if (mode.equalsIgnoreCase("4")) {
				mode = "Coupon";

			}

			System.out.println("Payment Processed using " + mode);
			System.out.println("Payment Processed successfully");
			System.out.println("Your pass will expire in 30 days..");

		}
		case 0:
			quit = true;
			break;
		default:
			System.out.println("Invalid choice.");
		}

	}

}