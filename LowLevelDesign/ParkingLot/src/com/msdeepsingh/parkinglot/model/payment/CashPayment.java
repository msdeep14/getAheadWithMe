package com.msdeepsingh.parkinglot.model.payment;

public class CashPayment extends Payment {
    public CashPayment() {
        super(PaymentType.CASH);
    }
}
