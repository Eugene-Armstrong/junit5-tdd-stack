package com.thoughtworks.tdd;

import java.util.ArrayList;

public class ParkingBoy {
    public ArrayList<ParkingLot> parkingLots;

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
        boolean result = false;
        for(ParkingLot parkingLot : parkingLots) {
            if (parkingLot.isFull()) {
                result = true;
            }else {
                result = false;
                break;
            }
        }
        return result;
    }

    public Receipt parking(Car car){
        Receipt r = null;
        for (ParkingLot parkingLot : parkingLots){
            if(!parkingLot.isFull()){
                r = parkingLot.park(car);
                break;
            }
        }
        if(r == null){
            throw new ParkingLotFullException();
        }
        return r;
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
