package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class NewParkingLot {
    public int size;
    public Map<Receipt,NewCar> parkedNewCars = new HashMap<>();

    public NewParkingLot(int size){
        setsize(size);
    }
    public int getsize() {
        return size;
    }

    public void setsize(int size) {
        this.size = size;
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
        this.size++;
        return this.parkedNewCars.get(receipt);
    }

    public boolean isFull(){
        return this.size == 0;
    }
}
