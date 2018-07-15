package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    public int size;
    public Map<Receipt,Car> parkedCars = new HashMap<>();

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Receipt, Car> getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(Map<Receipt, Car> parkedCars) {
        this.parkedCars = parkedCars;
    }

    public ParkingLot(int size){
        setSize(size);
    }

    public Receipt park(Car Car){
        if(isFull()){
            throw new ParkingLotFullException();
        }
        size--;
        Receipt key = new Receipt();
        UUID uuid = UUID.randomUUID();
        key.setReceiptUUID(uuid.toString());
        parkedCars.put(key, Car);
        return key;
    }

    public Car unPark(Receipt receipt){
        Car car = null;
        if(parkedCars.containsKey(receipt)){
            size++;
            car = parkedCars.remove(receipt);
        }
        return car;
    }

    public boolean isFull(){
        return this.size == 0;
    }
}
