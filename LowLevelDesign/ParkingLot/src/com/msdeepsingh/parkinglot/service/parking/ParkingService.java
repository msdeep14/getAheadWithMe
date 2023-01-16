package com.msdeepsingh.parkinglot.service.parking;

import com.msdeepsingh.parkinglot.model.parkingticket.ParkingTicket;
import com.msdeepsingh.parkinglot.model.vehicle.Vehicle;

public interface ParkingService {
    ParkingTicket parkVehicle(long entryGateNo, Vehicle vehicle);
}
