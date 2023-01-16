package com.msdeepsingh.parkinglot.model.parking;

import com.msdeepsingh.parkinglot.model.gate.EntranceGate;
import com.msdeepsingh.parkinglot.model.gate.ExitGate;

import java.util.Map;

public class ParkingFloor {
    private ParkingFloorNumber floorNo;
    private Map<Long, ParkingSpot> parkingSpotMap;
    private Map<Long, EntranceGate> entranceGateMap;
    private Map<Long, ExitGate> exitGateMap;

    public ParkingFloor(ParkingFloorNumber floorNo, Map<Long, ParkingSpot> parkingSpotMap,
                        Map<Long, EntranceGate> entranceGateMap,
                        Map<Long, ExitGate> exitGateMap) {
        this.floorNo = floorNo;
        this.parkingSpotMap = parkingSpotMap;
        this.entranceGateMap = entranceGateMap;
        this.exitGateMap = exitGateMap;
    }

    public ParkingFloorNumber getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(ParkingFloorNumber floorNo) {
        this.floorNo = floorNo;
    }

    public Map<Long, ParkingSpot> getParkingSpotMap() {
        return parkingSpotMap;
    }

    public void setParkingSpotMap(Map<Long, ParkingSpot> parkingSpotMap) {
        this.parkingSpotMap = parkingSpotMap;
    }

    public Map<Long, EntranceGate> getEntranceGateMap() {
        return entranceGateMap;
    }

    public void setEntranceGateMap(Map<Long, EntranceGate> entranceGateMap) {
        this.entranceGateMap = entranceGateMap;
    }

    public Map<Long, ExitGate> getExitGateMap() {
        return exitGateMap;
    }

    public void setExitGateMap(Map<Long, ExitGate> exitGateMap) {
        this.exitGateMap = exitGateMap;
    }

    @Override
    public String toString() {
        return "ParkingFloor{" +
                "floorNo=" + floorNo +
                ", parkingSpotMap=" + parkingSpotMap +
                ", entranceGateMap=" + entranceGateMap +
                ", exitGateMap=" + exitGateMap +
                '}';
    }
}
