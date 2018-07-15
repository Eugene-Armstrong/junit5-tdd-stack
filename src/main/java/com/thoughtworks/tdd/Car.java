package com.thoughtworks.tdd;

import java.util.Objects;

public class Car {
    private String carNum;

    public Car(){

    }
    public Car(String carNum) {
        this.carNum = carNum;
    }

    public String getCarNum() {
        return carNum;
    }
    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carNum, car.carNum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(carNum);
    }
}
