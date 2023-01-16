package com.msdeepsingh.parkinglot.model.vehicle;

/**
 * Two-wheeler
 * */
public class Bike extends Vehicle {
    public Bike(String vehicleNo) {
        super(vehicleNo, VehicleType.BIKE);
    }
}
