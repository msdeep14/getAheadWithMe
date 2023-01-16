package com.msdeepsingh.parkinglot.service.ticket;

import com.msdeepsingh.parkinglot.model.parkingticket.ParkingTicket;
import com.msdeepsingh.parkinglot.model.parkingticket.ParkingTicketStatus;
import com.msdeepsingh.parkinglot.model.vehicle.Vehicle;

public interface TicketService {
    public ParkingTicket getParkingTicket(Vehicle vehicle, long entryGateNo);
    public ParkingTicketStatus getParkingTicketStatus();
    public Double getParkingAmount();
}
