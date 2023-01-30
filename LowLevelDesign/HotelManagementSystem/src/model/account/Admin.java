package model.account;

import model.room.Room;

import java.util.List;

public class Admin extends Person {

    public boolean addRooms(List<Room> roomList) {

        return true;
    }

    public boolean upgradeRooms(List<Room> roomList) {

        return true;
    }

    //It can add new staff members-receptionist/housekeeping/managers/chefs etc.
    //There can be similar APIs to upgrade staff members or remove them from system on exit.
    public boolean addStaff(List<Person> staffList) {

        return true;
    }
}
