import com.msdeepsingh.parkinglot.model.gate.EntranceGate;
import com.msdeepsingh.parkinglot.model.gate.ExitGate;
import com.msdeepsingh.parkinglot.model.parking.*;
import com.msdeepsingh.parkinglot.model.parkingticket.ParkingTicket;
import com.msdeepsingh.parkinglot.model.vehicle.Vehicle;
import com.msdeepsingh.parkinglot.model.vehicle.VehicleType;
import com.msdeepsingh.parkinglot.service.impl.ParkingLotServiceImpl;
import com.msdeepsingh.parkinglot.service.ticket.impl.TicketServiceImpl;
import com.msdeepsingh.parkinglot.utility.ParkingUtility;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLotApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to Parking Lot!");

        ParkingLot parkingLot = ParkingLot.getParkingLot();

        ParkingLotServiceImpl parkingLotService = new ParkingLotServiceImpl(parkingLot);
        TicketServiceImpl ticketService = new TicketServiceImpl();

        //create parking spots
        Map<Long, ParkingSpot> parkingSpotMap = new HashMap<>();
        parkingSpotMap.put(1L, parkingLotService.addParkingSpot(ParkingUtility.createParkingSpot(1L, ParkingSpotType.FOUR_WHEELER), ParkingFloorNumber.GROUND));
        parkingSpotMap.put(3L, parkingLotService.addParkingSpot(ParkingUtility.createParkingSpot(2L, ParkingSpotType.TWO_WHEELER), ParkingFloorNumber.GROUND));
        parkingSpotMap.put(4L, parkingLotService.addParkingSpot(ParkingUtility.createParkingSpot(3L, ParkingSpotType.FOUR_WHEELER), ParkingFloorNumber.GROUND));
        parkingSpotMap.put(5L, parkingLotService.addParkingSpot(ParkingUtility.createParkingSpot(4L, ParkingSpotType.TWO_WHEELER), ParkingFloorNumber.GROUND));

        //create entry and entrance gate for ground floor
        EntranceGate entranceGate = new EntranceGate(1L, ParkingFloorNumber.GROUND, true);
        ExitGate exitGate = new ExitGate(2L, ParkingFloorNumber.GROUND, true);
        Map<Long, EntranceGate> entranceGateMap = new HashMap<>();
        entranceGateMap.put(1L, entranceGate);

        Map<Long, ExitGate> exitGateMap = new HashMap<>();
        exitGateMap.put(2L, exitGate);


        //create Parking Floors
        Map<ParkingFloorNumber, ParkingFloor> parkingFloorMap = new HashMap<>();
        ParkingFloor groundParkingFloor = new ParkingFloor(ParkingFloorNumber.GROUND, parkingSpotMap, entranceGateMap, exitGateMap);
        parkingFloorMap.put(ParkingFloorNumber.GROUND, groundParkingFloor);

        //Initialize parking Lot with name identifier and parking floors information
        parkingLot.setParkingLotId(UUID.randomUUID().toString());
        parkingLot.setParkingLotName("Mandeep ka parking lot");
        parkingLot.setParkingFloorMap(parkingFloorMap);

        //create vehicle
        Vehicle myHondaCar = new Vehicle("binaNumberWaliGadi", VehicleType.CAR);
        Vehicle myHeroWaliMotorCycle = new Vehicle("127.0.0.1", VehicleType.BIKE);

        //getParkingTicket
        ParkingTicket carTicket = ticketService.getParkingTicket(myHondaCar, 1L);
        System.out.println("Ticket generated successfully for vehicle: "+myHondaCar.getVehicleNo());
        System.out.println("Ticket Details: "+carTicket);
    }
}