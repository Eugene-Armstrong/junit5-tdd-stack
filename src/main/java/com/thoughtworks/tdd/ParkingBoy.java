package com.thoughtworks.tdd;

import java.util.ArrayList;

public class ParkingBoy {
    public ArrayList<NewParkingLot> newParkingLots;
    public Receipt receipt;

    public ParkingBoy(ArrayList<NewParkingLot> newParkingLots){
        setNewParkingLots(newParkingLots);
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public ArrayList<NewParkingLot> getNewParkingLots() {
        return newParkingLots;
    }

    public void setNewParkingLots(ArrayList<NewParkingLot> newParkingLots) {
        this.newParkingLots = newParkingLots;
    }

    public boolean isParkingLotsFull(){
        boolean result = false;
        for(NewParkingLot newParkingLot : newParkingLots) {
            if (newParkingLot.size == 0) {
                result = true;
            }else {
                result = false;
                break;
            }
        }
        return result;
    }

    public Receipt park(ArrayList<NewParkingLot> newParkingLots){
        if(!isParkingLotsFull()){
            for (NewParkingLot newParkingLot : newParkingLots){
                if(!newParkingLot.isFull()){
                    receipt = newParkingLot.park(new NewCar());
                }
            }
            return receipt;
        }else {
            throw new ParkingLotFullException();
        }
    }

    public NewCar unPark(Receipt receipt){
        NewCar car = new NewCar();
        for (NewParkingLot newParkingLot : newParkingLots){
            if(newParkingLot.getParkedNewCars().containsKey(receipt)){
                car = newParkingLot.unPark(receipt);
            }
        }
        return car;
    }
}
