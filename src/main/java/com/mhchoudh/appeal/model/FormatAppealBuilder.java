package com.mhchoudh.appeal.model;

public class FormatAppealBuilder {
    
//    private double amount = 10.0f;
    private String emailRecipient = "Frank Calliss";
    private String emailSender = "Frankk.Callis@asu.edu";
    private String subject = "APPEAL CSE564 :";
    private int salutation;
    
    public static FormatAppealBuilder formatAppeal() {
        return new FormatAppealBuilder();
    }

    
    public FormatAppealBuilder withCardholderName(String name) {
        this.emailRecipient = name;
        return this;
    }
    
    public FormatAppealBuilder withCardNumber(String cardNumber) {
        this.emailSender = cardNumber;
        return this;
    }
    
    public FormatAppealBuilder withExpiryMonth(String month) {

            this.subject= month;
        
        return this;
    }
    
    public FormatAppealBuilder withExpiryYear(int year) {
  
            this.salutation= year;
        
        return this;
    }

    public AppealFormat build() {
        return new AppealFormat(emailRecipient, emailSender, subject, salutation);
    }
}
