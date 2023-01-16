package com.msdeepsingh.parkinglot.model.gate;

import com.msdeepsingh.parkinglot.model.parking.ParkingFloorNumber;

public class EntranceGate extends ParkingGate {

    public EntranceGate(long gateNo, ParkingFloorNumber floorNo, boolean isOpen) {
        super(gateNo, floorNo, isOpen);
    }
}
