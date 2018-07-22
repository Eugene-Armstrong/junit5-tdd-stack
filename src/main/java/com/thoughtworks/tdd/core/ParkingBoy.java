package com.thoughtworks.tdd.core;

import java.util.ArrayList;

public class ParkingBoy {
    private ArrayList<ParkingLot> parkingLots;

    public ParkingBoy(ArrayList<ParkingLot> parkingLots){
        setParkingLots(parkingLots);
    }

    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public boolean isParkingLotsFull(){
        boolean result = true;
        for(ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()) {
                result = false;
            }
        }
        return result;
    }

    public Receipt parking(Car car){
        Receipt receipt = null;
        for (ParkingLot parkingLot : parkingLots){
            if(!parkingLot.isFull()){
                receipt = parkingLot.park(car);
                break;
            }
        }
        if(receipt == null){
            throw new ParkingLotFullException();
        }
        return receipt;
    }

    public Car unPark(Receipt receipt){
        Car car = null;
        for (ParkingLot parkingLot : parkingLots){
            if(parkingLot.getParkedCars().containsKey(receipt)){
                car = parkingLot.unPark(receipt);
                break;
            }
        }
        if(car==null) {
            throw new WrongReceiptException();
        }
        return car;
    }
}
