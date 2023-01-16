package com.msdeepsingh.parkinglot.model.parking;

import com.msdeepsingh.parkinglot.model.vehicle.Vehicle;
import com.msdeepsingh.parkinglot.model.vehicle.VehicleType;

public class ParkingSpot {
    private long spotNo;
    private Vehicle vehicle;
    /**
     * VehicleType is added here so as system is extensible in a way that two-wheelers can also be parked
     * at four wheeler parking space --> extended system requirement
     * */
    private VehicleType vehicleType;
    private ParkingSpotType parkingSpotType;
    private boolean isFree;

    /**
     * Creates default ParkingSpot with availability=true
     * */
    public ParkingSpot(long spotNo, ParkingSpotType parkingSpotType) {
        this.spotNo = spotNo;
        this.parkingSpotType = parkingSpotType;
        this.isFree = true;
    }
    public ParkingSpot(long spotNo, Vehicle vehicle, VehicleType vehicleType, ParkingSpotType parkingSpotType, boolean isFree) {
        this.spotNo = spotNo;
        this.vehicle = vehicle;
        this.vehicleType = vehicleType;
        this.parkingSpotType = parkingSpotType;
        this.isFree = isFree;
    }

    public long getSpotNo() {
        return spotNo;
    }

    public void setSpotNo(long spotNo) {
        this.spotNo = spotNo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void setParkingSpotType(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
