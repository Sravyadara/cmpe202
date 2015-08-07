package com.cmpe202.rules;

public class PaymentRules extends Rules{


    private String cardNumber;
    private String cvv;
    private String expiryDate;

    public String validate() {

        long total;
        String validationStatus = "validation failed";

        total = sumOfEvenPlaces(Long.parseLong(cardNumber)) + sumOfOddPlaces(Long.parseLong(cardNumber));
        if(isnotNullcheck(cardNumber)){
            if(isValid(total)) {
                validationStatus = "validation success";
            }
        }
        return validationStatus;
    }
    public PaymentRules(String cardNumber, String cvv, String expiryDate){
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public boolean isnotNullcheck(String cardNumber){
        if(cardNumber == null /*|| licenseNumber == null*/)
            return false;
        else
            return true;

    }
    public static boolean isValid(long total) {
        if (total % 10 == 0) {
            return true;
        }
        return false;
    }
    public static int sumOfEvenPlaces(long number) {
        int sum = 0;
        int remainder;
        number %= 10;
        while (number % 10 != 0 || number / 10 != 0) {
            remainder = (int)(number % 10);
            sum = sum + getDigit(remainder * 2);
            number /= 100;
        }
        return sum;

    }
    public static int getDigit(int number) {
        if (number <= 9) {
            return number;
        } else if (number > 9)
            return (number % 10 + number / 10);

        return number;
    }

    public static int sumOfOddPlaces(long number) {
        int sum = 0;
        int remainder;
        number /= 10;
        while(number % 10 != 0 || number / 10 != 0) {
            remainder = (int)(number % 10);
            sum = sum + getDigit(remainder * 2);
            number /= 100;
        }
        System.out.println(sum);
        return sum;
    }

    public static int getSize(long number) {
        int len = 0;
        while (number >= 10) {
            number /= 10;
            len++;
        }
        return len;
    }

}
