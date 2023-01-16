package com.msdeepsingh.parkinglot.service;

import com.msdeepsingh.parkinglot.model.gate.EntranceGate;
import com.msdeepsingh.parkinglot.model.gate.ExitGate;
import com.msdeepsingh.parkinglot.model.parking.ParkingFloor;
import com.msdeepsingh.parkinglot.model.parking.ParkingFloorNumber;
import com.msdeepsingh.parkinglot.model.parking.ParkingSpot;

/**
 * Interface expose functions for basic utility of ParkingLot Application to setup infrastructure for
 * application working.
 * like setting up ParkingFloor/gates/parkingSpots.
 * */
public interface ParkingLotService {
    ParkingSpot addParkingSpot(ParkingSpot parkingSpot, ParkingFloorNumber floorNo);
    void removeParkingSpot(long spotNumber); // return nothing on success, exception on failure
    ParkingFloor addParkingFloor(ParkingFloor parkingFloor);
    void removeParkingFloor(ParkingFloorNumber floorNo);
    EntranceGate addEntranceGate(ParkingFloorNumber floorNo, long gateNo);
    void removeEntranceGate(ParkingFloorNumber floorNo, long gateNo);
    ExitGate addExitGate(ParkingFloorNumber floorNo, long gateNo);
    void removeExitGate(ParkingFloorNumber floorNo, long gateNo);
}
