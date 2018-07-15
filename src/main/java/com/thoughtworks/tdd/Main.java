package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();

        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(1);

        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingSystem parkingSys = new ParkingSystem();

        parkingSys.runSys(parkingBoy);
    }
}
