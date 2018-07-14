package com.thoughtworks.tdd;

import java.util.ArrayList;

public class ParkingBoy {
    public ArrayList<ParkingLot> parkingLots;
    public Receipt receipt;

    public ParkingBoy(ArrayList<ParkingLot> parkingLots){
        setParkingLots(parkingLots);
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
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
            if (parkingLot.size == 0) {
                result = true;
            }else {
                result = false;
                break;
            }
        }
        return result;
    }

    public Receipt park(Car car){
        if(!isParkingLotsFull()){
            for (ParkingLot parkingLot : parkingLots){
                if(!parkingLot.isFull()){
                    receipt = parkingLot.park(car);
                }
            }
            return receipt;
        }else {
            throw new ParkingLotFullException();
        }
    }


    public Car unPark(Receipt receipt){
        Car car = new Car();
        for (ParkingLot parkingLot : parkingLots){
            if(parkingLot.getParkedNewCars().containsKey(receipt)){
                car = parkingLot.unPark(receipt);
            }
        }
        return car;
    }
}
