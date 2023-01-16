package com.msdeepsingh.parkinglot.service.parking.impl;

import com.msdeepsingh.parkinglot.model.parkingticket.ParkingTicket;
import com.msdeepsingh.parkinglot.model.vehicle.Vehicle;
import com.msdeepsingh.parkinglot.service.parking.ParkingService;

public class ParkingServiceImpl implements ParkingService {
    @Override
    public ParkingTicket parkVehicle(long entryGateNo, Vehicle vehicle) {
        //find available slot via entryGateNo
        //assign the slot to vehicle
        //Generate Ticket with Payment PENDING
        //mark slot as filled.
        return null;
    }
}
