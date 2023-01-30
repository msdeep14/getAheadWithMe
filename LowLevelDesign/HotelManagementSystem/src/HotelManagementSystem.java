import model.hotel.Hotel;

/**
 * Problem Statement - Design a Hotel Management System that can be used to manage the hotel operations.
 * Example - Manage all internal operations via software for "MsDeep Singh Resorts"
 *
 * Requirements --->
 * Hotel Administrator -->
 * 1. Manage Rooms at hotel - add new room, remove existing rooms, upgrade existing rooms.
 * 2. Add/Update new staff members to system.
 * 3. Access to all the operations which can be done by receptionist/customers.
 *
 * Hotel Receptionist -->
 * 1. CheckIn/CheckOut customers.
 * 2. View Room details - who is staying in specific room, till what date etc.
 * 3. All the operations which can be done by customers -- as customers can directly come to desk for booking instead of online booking.
 *
 * Customers -->
 * 1. Reserve Room/Upgrade room
 * 2. Make Payment - credit card/cash/debit card
 * 3. Get booking ticket
 * 4. View my bookings.
 * 5. LogIn/LogOut to system.
 * 6. View for available rooms in Hotels with their pricing. - logIn not required
 * 7. Cancel a room - should be allowed within specific time range - example: can cancel within 24hrs of booking/checkIn date.
 * 8. Order room services - inRoomDining, MiniBar etc.
 *
 * System -->
 * 1. Send notification for confirmed booking.
 * 2.
 *
 *
 * NOTE - The entire codebase is not implemented. If you find the time, please make a pull request. Happy Learning :)
 * */
public class HotelManagementSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to MsDeep Singh Resorts!!!");

        final Hotel hotel = Hotel.getInstance();
        //add rooms to hotel
        //add staff

        //check for available rooms
        //book room
        //view my booking
    }
}
