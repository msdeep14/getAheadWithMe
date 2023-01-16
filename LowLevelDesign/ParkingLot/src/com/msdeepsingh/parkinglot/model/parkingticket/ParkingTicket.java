package com.msdeepsingh.parkinglot.model.parkingticket;

import com.msdeepsingh.parkinglot.model.parking.ParkingSpot;
import com.msdeepsingh.parkinglot.model.payment.Payment;
import com.msdeepsingh.parkinglot.model.vehicle.Vehicle;

public class ParkingTicket {
    private long token;
    private Vehicle vehicle;
    private long entryTime;
    private long entryGateNumber;
    private ParkingSpot parkingSpot;
    private Payment payment;

    public ParkingTicket(long token, Vehicle vehicle, long entryTime, long entryGateNumber,
                         ParkingSpot parkingSpot, Payment payment) {
        this.token = token;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.entryGateNumber = entryGateNumber;
        this.parkingSpot = parkingSpot;
        this.payment = payment;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public long getEntryGateNumber() {
        return entryGateNumber;
    }

    public void setEntryGateNumber(long entryGateNumber) {
        this.entryGateNumber = entryGateNumber;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Payment getTicketStatus() {
        return payment;
    }

    public void setTicketStatus(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "token=" + token +
                ", vehicle=" + vehicle +
                ", entryTime=" + entryTime +
                ", entryGateNumber=" + entryGateNumber +
                ", parkingSpot=" + parkingSpot +
                ", payment=" + payment +
                '}';
    }
}
