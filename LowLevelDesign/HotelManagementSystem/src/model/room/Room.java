package model.room;

import java.util.List;

public class Room {
    private long roomNumber;
    private RoomType roomType;
    private double roomPrice;
    private RoomStatus roomStatus;
    private List<RoomKey> roomKeyList; //At-least one master key will always be assigned to room.

    //APIs
    public boolean roomCheckIn() {
        return true;
    }

    public boolean roomCheckOut() {
        return true;
    }

    public boolean isRoomAvailable() {
        return true;
    }
}
