package model.booking;

import model.common.DateRange;
import model.room.Room;

import java.util.List;

public class Booking {
    private String bookingReferenceNumber;
    private DateRange dateRange;
    private BookingStatus bookingStatus;
    private String guestReferenceNumber;
    private List<Room> roomList;
    private Payment paymentDetails;

    public Booking getBookingDetails(String bookingReferenceNumber) {

        return new Booking();
    }
}
