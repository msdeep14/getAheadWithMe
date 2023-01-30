package model.hotel;

import model.room.Room;
import model.room.RoomType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    private String hotelId;
    private String hotelName;
    private String address; //create custom class Address --> and use it at other places as well such as staff home address

    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, RoomType roomType) {

        return new ArrayList<>();
    }

    //other APIs - get users registered, working staff members working, etc.

    private Hotel() {

    }

    private static Hotel hotel = null;
    //Hotel is parent entity and only single instance should be created of it.
    public static Hotel getInstance() {
        if(hotel == null) {
            return new Hotel();
        }
        return hotel;
    }
}
