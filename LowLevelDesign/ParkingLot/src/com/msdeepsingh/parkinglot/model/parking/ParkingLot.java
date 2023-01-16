package com.msdeepsingh.parkinglot.model.parking;

import java.util.Map;
import java.util.Objects;

/**
 * System Hierarchy --->
 * Complete parking area is represented by {@link ParkingLot};
 * {@link ParkingLot} constitutes multiple {@link ParkingFloor};
 * {@link ParkingFloor} constitutes multiple {@link ParkingSpot}.
 * */
public class ParkingLot {
    private Map<ParkingFloorNumber, ParkingFloor> parkingFloorMap;
    /**
     * Same system may be running on multiple parking lot locations,
     * parkingSpaceId will uniquely identify {@link ParkingLot}
     *
     * more params can be added such as Address as per requirement.
     * */
    private String parkingLotId;
    private String parkingLotName;


    private static ParkingLot parkingLot = null;

    /**
     * Private Constructor to ensure Singleton behavior.
     * */
    private ParkingLot() {
        //get information from database to load
        //Example - parking floor, parking spots, entrance/exit gates
        //current status of parkingLot - booked/available slots
    }

    public static ParkingLot getParkingLot() {
        if(Objects.isNull(parkingLot)) {
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }

    public Map<ParkingFloorNumber, ParkingFloor> getParkingFloorMap() {
        return parkingFloorMap;
    }

    public void setParkingFloorMap(Map<ParkingFloorNumber, ParkingFloor> parkingFloorMap) {
        this.parkingFloorMap = parkingFloorMap;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }
}
