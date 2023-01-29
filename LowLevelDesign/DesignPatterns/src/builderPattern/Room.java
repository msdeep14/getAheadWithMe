package builderPattern;

public class Room {
    private String roomNumber;
    private String roomType;
    private String roomPrice;
    //other attributes

    Room(RoomBuilder builder) {
        this.roomNumber = builder.roomNumber;
        this.roomType = builder.roomType;
        this.roomPrice = builder.roomPrice;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomPrice='" + roomPrice + '\'' +
                '}';
    }
}
