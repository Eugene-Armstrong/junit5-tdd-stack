package com.thoughtworks.tdd.Model;

import com.thoughtworks.tdd.Exception.ParkingLotFullException;
import com.thoughtworks.tdd.Exception.WrongReceiptException;

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
        boolean result = true;
        for(ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()) {
                result = false;
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
//                break;
            }
        }
        if(car==null) {
            throw new WrongReceiptException();
        }
        return car;
    }
}
