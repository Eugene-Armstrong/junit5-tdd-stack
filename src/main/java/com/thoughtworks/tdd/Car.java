package com.thoughtworks.tdd;

public class Car {

    public void leave(){

    }

    public boolean isGoingToPark(ParkingLot parkingLot){
        return parkingLot.isParkingLotAvailable();
//        ParkingLot parkingLot = new ParkingLot();
//        parkingLot.setCurrentNum(parkingLot.getCurrentNum()+1);
    }
}
