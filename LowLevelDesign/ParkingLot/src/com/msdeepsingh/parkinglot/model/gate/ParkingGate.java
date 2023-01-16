package com.msdeepsingh.parkinglot.model.gate;

import com.msdeepsingh.parkinglot.model.parking.ParkingFloorNumber;

public abstract class ParkingGate {
    /**
     * For unique representation of gates across the floor, the gate numbers are not repeated.
     * */
    private long gateNo;
    private ParkingFloorNumber floorNo;
    private boolean isOpen;

    public ParkingGate(long gateNo, ParkingFloorNumber floorNo, boolean isOpen) {
        this.gateNo = gateNo;
        this.floorNo = floorNo;
        this.isOpen = isOpen;
    }

    public long getGateNo() {
        return gateNo;
    }

    public void setGateNo(long gateNo) {
        this.gateNo = gateNo;
    }

    public ParkingFloorNumber getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(ParkingFloorNumber floorNo) {
        this.floorNo = floorNo;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
