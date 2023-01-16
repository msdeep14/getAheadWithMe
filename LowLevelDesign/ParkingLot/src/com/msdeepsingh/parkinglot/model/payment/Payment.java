package com.msdeepsingh.parkinglot.model.payment;

public class Payment {
    //time in epoch
    private long paymentDate;
    private Amount amount;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;

    public Payment(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
