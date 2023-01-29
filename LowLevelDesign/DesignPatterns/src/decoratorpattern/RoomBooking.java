package decoratorpattern;

import java.util.Date;
import java.util.List;

public class RoomBooking {
    private String bookingId;
    private Date checkInDate;
    private Date checkOutDate;
    //other attributes like booking status, payment status, guest details
    List<Room> roomList;
    private BaseRoomCharge totalRoomCharge; //base room charges, extra charge basis type of room, laundry charges, in-room dining, lounge charges, minibar charges etc.
}
