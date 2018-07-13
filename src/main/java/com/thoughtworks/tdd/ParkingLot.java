package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public int size;
    public Map<Receipt,Car> parkedNewCars = new HashMap<>();
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Receipt, Car> getParkedNewCars() {
        return parkedNewCars;
    }

    public void setParkedNewCars(Map<Receipt, Car> parkedNewCars) {
        this.parkedNewCars = parkedNewCars;
    }

    public ParkingLot(int size){
        setSize(size);
    }

    public Receipt park(Car Car){
        if(size == 0){
            throw new ParkingLotFullException();
        }
        this.size--;

        Receipt key = new Receipt();
        this.parkedNewCars.put(key, Car);
        return key;
    }

    public Car unPark(Receipt receipt){
        Car car = new Car();
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
