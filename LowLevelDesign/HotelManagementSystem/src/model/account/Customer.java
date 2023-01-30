package model.account;

import model.booking.Booking;

public class Customer extends Person {

    public boolean createBooking() {

        return true;
    }

    public Booking viewBooking() {
        return new Booking();
    }
}
