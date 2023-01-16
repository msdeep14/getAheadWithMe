package com.msdeepsingh.parkinglot.model.gate;

import com.msdeepsingh.parkinglot.model.parking.ParkingFloorNumber;

public class ExitGate extends ParkingGate {

    public ExitGate(long gateNo, ParkingFloorNumber floorNo, boolean isOpen) {
        super(gateNo, floorNo, isOpen);
    }
}
