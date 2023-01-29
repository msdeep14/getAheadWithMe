package builderPattern;

public class RoomBuilder {
    //data members should not be private
    String roomNumber;
    String roomType;
    String roomPrice;

    //other attributes

    public RoomBuilder setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public RoomBuilder setRoomType(String roomType) {
        this.roomType = roomType;
        return this;
    }

    public RoomBuilder setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
        return this;
    }

    public Room build() {
        return new Room(this);
    }
}
