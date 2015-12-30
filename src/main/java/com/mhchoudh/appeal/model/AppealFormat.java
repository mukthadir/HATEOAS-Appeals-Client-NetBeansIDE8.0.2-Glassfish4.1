package com.mhchoudh.appeal.model;

import org.joda.time.DateTime;

public class AppealFormat {

//    private final double amount;
    private final String emailReceipient;
    private final String emailSender;
    private final String subject;
    private final int bodyOfEmail;
    private DateTime appealDate;

    public AppealFormat(String cardholderName, String cardNumber, String expiryMonth, int expiryYear) {
//        this.amount = amount;
        this.emailReceipient = cardholderName;
        this.emailSender = cardNumber;
        this.subject = expiryMonth;
        this.bodyOfEmail = expiryYear;
        appealDate = new DateTime();

    }


    public String getEmailReceipient() {
        return emailReceipient;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public String getSubject() {
        return subject;
    }

    public int getExpiryYear() {
        return bodyOfEmail;
    }
    public DateTime getAppealDate() {
        return appealDate;
    }
}
