package service;

public class BookingService {
    //It can be good practice to expose APIs via a service layer instead of defining them in model itself.
    //
    //The System will definitely need to query some kind of database to retrieve booking details or search for available
    //rooms.
    //create a dao layer for database operations

    //Further for payment related processing, system will need to integrate with subsystems such as Razorpay for online
    //payment facility.
    //create a domain layer [Adapter pattern] to expose simple payment APIs that can handle translation from third party
    //integrations.

}
