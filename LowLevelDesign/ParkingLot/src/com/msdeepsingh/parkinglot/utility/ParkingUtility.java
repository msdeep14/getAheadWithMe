package com.msdeepsingh.parkinglot.utility;

import com.msdeepsingh.parkinglot.model.parking.ParkingSpot;
import com.msdeepsingh.parkinglot.model.parking.ParkingSpotType;

public class ParkingUtility {
    public static ParkingSpot createParkingSpot(long spotNumber, ParkingSpotType parkingSpotType) {
        return new ParkingSpot(spotNumber, parkingSpotType);
    }
}
