package com.cmpe202.rules;

public class PaymentRules extends Rules{


	private String cardNumber;
	private String cvv;
	private String expiryDate;

	public boolean validate() {

		long total;
		boolean validationStatus = false;

		total = sumOfDoubleEvenPlace(Long.parseLong(cardNumber)) + sumOfOddPlace(Long.parseLong(cardNumber));
		if(isnotNullcheck(cardNumber)){
			if(isValid(total)) {
				validationStatus = true;
			}
		}
		return validationStatus;
	}
	public PaymentRules(String cardNumber, String cvv, String expiryDate){
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
	}

	public PaymentRules() {
		// TODO Auto-generated constructor stub
	}
	public boolean isnotNullcheck(String cardNumber){
		if(cardNumber == null /*|| licenseNumber == null*/)
			return false;
		else
			return true;

	}
	public  boolean isValid(long total) {
		if (total % 10 == 0) {
			return true;
		}
		return false;
	}
	public  int sumOfOddPlace(long number) {
        int result = 0;

        while (number > 0) {
            result += (int) (number % 10);
            number = number / 100;
        }

        return result;
    }
	public  int getDigit(int number) {

        if (number <= 9) {
            return number;
        } else {
            int firstDigit = number % 10;
            int secondDigit = (int) (number / 10);

            return firstDigit + secondDigit;
        }
    }

	public  int sumOfDoubleEvenPlace(long number) {

        int result = 0;
        long temp = 0;

        while (number > 0) {
            temp = number % 100;
            result += getDigit((int) (temp / 10) * 2);
            number = number / 100;
        }

        return result;
    }

	public  int getSize(long number) {
		int len = 0;
		while (number >= 10) {
			number /= 10;
			len++;
		}
		return len;
	}

}