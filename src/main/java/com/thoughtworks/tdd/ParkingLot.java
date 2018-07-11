package com.thoughtworks.tdd;

public class ParkingLot {
    public int parkingTotal;
    public int currentNum;

    public ParkingLot(int parkingTotal,int currentNum){
        this.parkingTotal = parkingTotal;
        this.currentNum = currentNum;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public int getParkingTotal() {
        return parkingTotal;
    }

    public void setParkingTotal(int parkingTotal) {
        this.parkingTotal = parkingTotal;
    }

    public boolean isParkingLotAvailable(){
        boolean isAvailable= currentNum < parkingTotal?true:false;
        notice(isAvailable);
        return isAvailable;
    }

    public String notice(boolean isAvailable){
        String result = isAvailable?"Welcome!":"Sorry,Parking-Lot is not available now!";
        return result;
    }

    public void park(Car car){
        if(car.isGoingToPark(this)){
            currentNum++;
        }
    }
}
