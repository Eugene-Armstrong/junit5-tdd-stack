package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingLotTest {

    @Test
    public void should_return_parking_lot_is_available(){
        ParkingLot parkingLot = new ParkingLot(30,20);
        //when
        boolean result = parkingLot.isParkingLotAvailable();
        //then
        assertThat(result ,is(true));
    }

    @Test
    public void should_return_parking_lot_is_not_available(){
        //given
        ParkingLot parkingLot = new ParkingLot(30,30);
        //when
        boolean result = parkingLot.isParkingLotAvailable();
        //then
        assertThat(result ,is(false));
    }

    @Test
    public void should_return_welcome_from_parking_lot(){
        //given
        ParkingLot parkingLot = new ParkingLot(30,29);
        //when
        boolean isAvailable = parkingLot.isParkingLotAvailable();
        String result = parkingLot.notice(isAvailable);
        //then
        assertThat(result ,is("Welcome!"));
    }

    @Test
    public void should_return_sorry_from_parking_lot(){
        //given
        ParkingLot parkingLot = new ParkingLot(30,30);
        //when
        boolean isAvailable = parkingLot.isParkingLotAvailable();
        String result = parkingLot.notice(isAvailable);
        //then
        assertThat(result ,is("Sorry,Parking-Lot is not available now!"));
    }

    @Test
    public void should_park_successfully(){
        //given
        ParkingLot parkingLot = new ParkingLot(30,20);
        Car car = new Car();
        //when
        car.isGoingToPark(parkingLot);
        parkingLot.park(car);
        int result = parkingLot.currentNum;
        //then
        assertThat(result ,is(21));
    }
}
