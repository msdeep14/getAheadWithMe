package com.msdeepsingh.parkinglot.service.impl;

import com.msdeepsingh.parkinglot.exception.NonRetryableException;
import com.msdeepsingh.parkinglot.model.gate.EntranceGate;
import com.msdeepsingh.parkinglot.model.gate.ExitGate;
import com.msdeepsingh.parkinglot.model.parking.ParkingFloor;
import com.msdeepsingh.parkinglot.model.parking.ParkingFloorNumber;
import com.msdeepsingh.parkinglot.model.parking.ParkingLot;
import com.msdeepsingh.parkinglot.model.parking.ParkingSpot;
import com.msdeepsingh.parkinglot.service.ParkingLotService;

import java.util.*;

public class ParkingLotServiceImpl implements ParkingLotService {
    ParkingLot parkingLot;

    public ParkingLotServiceImpl(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    @Override
    public ParkingSpot addParkingSpot(ParkingSpot parkingSpot, ParkingFloorNumber floorNo) {
        Map<Long, ParkingSpot> parkingSpotMap;
        if(Objects.isNull(parkingLot.getParkingFloorMap()) || Objects.isNull(parkingLot.getParkingFloorMap().get(floorNo))
                || Objects.isNull(parkingLot.getParkingFloorMap().get(floorNo).getParkingSpotMap())) {
            parkingSpotMap = new HashMap<>();
        } else {
            parkingSpotMap = parkingLot.getParkingFloorMap().get(floorNo).getParkingSpotMap();
        }
        if(parkingSpotMap.containsKey(parkingSpot.getSpotNo())) {
            throw new NonRetryableException(parkingSpot.getSpotNo() +
                    " is already present in parkingLot, please assign new spot number");
        }
        parkingSpotMap.put(parkingSpot.getSpotNo(), parkingSpot);
        return parkingSpot;
    }

    @Override
    public void removeParkingSpot(long spotNumber) {

    }

    @Override
    public ParkingFloor addParkingFloor(ParkingFloor parkingFloor) {
        Map<ParkingFloorNumber, ParkingFloor> parkingFloorMap = parkingLot.getParkingFloorMap();
        if(Objects.isNull(parkingFloorMap)) {
            parkingFloorMap = new HashMap<>();
        }
        parkingFloorMap.put(ParkingFloorNumber.GROUND, parkingFloor);
        parkingLot.setParkingFloorMap(parkingFloorMap);
        return parkingFloor;
    }

    @Override
    public void removeParkingFloor(ParkingFloorNumber floorNo) {

    }

    @Override
    public EntranceGate addEntranceGate(ParkingFloorNumber floorNo, long gateNo) {
        return new EntranceGate(gateNo, floorNo, true);
    }

    @Override
    public void removeEntranceGate(ParkingFloorNumber floorNo, long gateNo) {

    }

    @Override
    public ExitGate addExitGate(ParkingFloorNumber floorNo, long gateNo) {
        return new ExitGate(gateNo, floorNo, true);
    }

    @Override
    public void removeExitGate(ParkingFloorNumber floorNo, long gateNo) {

    }
}
