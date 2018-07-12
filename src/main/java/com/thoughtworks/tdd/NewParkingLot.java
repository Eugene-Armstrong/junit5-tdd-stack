package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class NewParkingLot {
    public int size;
    public Map<Receipt,NewCar> parkedNewCars = new HashMap<>();
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Receipt, NewCar> getParkedNewCars() {
        return parkedNewCars;
    }

    public void setParkedNewCars(Map<Receipt, NewCar> parkedNewCars) {
        this.parkedNewCars = parkedNewCars;
    }

    public NewParkingLot(int size){
        setSize(size);
    }

    public Receipt park(NewCar NewCar){
        if(size == 0){
            throw new ParkingLotFullException();
        }
        this.size--;

        Receipt key = new Receipt();
        this.parkedNewCars.put(key,NewCar);
        return key;
    }

    public NewCar unPark(Receipt receipt){
        NewCar car = new NewCar();
        if(getParkedNewCars().containsKey(receipt)){
            this.size++;
            car = this.parkedNewCars.get(receipt);
        }
        return car;
    }

    public boolean isFull(){
        return this.size == 0;
    }
}
