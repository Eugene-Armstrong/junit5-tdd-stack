package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ParkingBoyTest {

    @Test
    public void should_park_successfully_when_given_parking_lots_are_not_full() {
        Receipt receipt = new Receipt();
        Car car = new Car();
        ParkingLot parkingLot1 = mock(ParkingLot.class);

        when(parkingLot1.isFull()).thenReturn(false);
        when(parkingLot1.park(car)).thenReturn(receipt);

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        try {
            parkingBoy.parking(car);
            verify(parkingLot1).park(car);
        } catch (ParkingLotFullException exception) {
            fail("Parking boy should park successfully");
        }
    }

    @Test
    public void should_park_not_successfully_when_given_parking_lots_are_full() {
        Receipt receipt = new Receipt();
        Car car = new Car();
        ParkingLot parkingLot1 = mock(ParkingLot.class);

        when(parkingLot1.isFull()).thenReturn(true);
        when(parkingLot1.park(car)).thenReturn(receipt);

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        try {
            parkingBoy.parking(car);
            verify(parkingLot1).park(car);
            fail("Parking boy should not park successfully");
        } catch (ParkingLotFullException exception) {
        }

//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        ParkingLot parkingLot1 = new ParkingLot(0);
//        ParkingLot parkingLot2 = new ParkingLot(0);
//        parkingLots.add(parkingLot1);
//        parkingLots.add(parkingLot2);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
//        try {
//            parkingBoy.parking(new Car());
//            fail("Parking boy should not parking successfully");
//        } catch (ParkingLotFullException exception) {
//        }
    }

    @Test
    public void should_get_specific_car_when_call_unPark_from_parking_boy_given_receipt_is_right(){

//        Receipt receipt = new Receipt();
//        Car car = new Car();
//
//        ParkingLot parkingLot1 = mock(ParkingLot.class);
//        when(parkingLot1.isFull()).thenReturn(false);
//        when(parkingLot1.park(car)).thenReturn(receipt);
//        when(parkingLot1.unPark(receipt)).thenReturn(car);
//
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        parkingLots.add(parkingLot1);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
//
//        try {
//            receipt = parkingBoy.parking(car);
//            assertThat(parkingBoy.unPark(receipt), is(car));
//            verify(parkingLot1).park(car);
//            verify(parkingLot1).unPark(receipt);
//        } catch (WrongReceiptException exception) {
//            fail("Parking boy should get the car by right receipt");
//        }

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        Receipt receipt = parkingBoy.parking(car);
        try {
            parkingBoy.unPark(receipt);
        } catch (WrongReceiptException exception) {
            fail("Parking boy should get the car by right receipt");
        }
    }

    @Test
    public void should_not_get_specific_car_when_call_unPark_from_parking_boy_given_receipt_is_wrong(){

        Receipt receipt = new Receipt();
        Car car = new Car();

        ParkingLot parkingLot1 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false);
        when(parkingLot1.park(car)).thenReturn(receipt);
        when(parkingLot1.unPark(receipt)).thenReturn(car);

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        try {
            receipt = parkingBoy.parking(car);
            assertThat(parkingBoy.unPark(receipt), is(car));
            verify(parkingLot1).park(car);
            verify(parkingLot1).unPark(receipt);
            fail("Parking boy should not get the car by wrong receipt");
        } catch (WrongReceiptException exception) {
        }

//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        ParkingLot parkingLot1 = new ParkingLot(2);
//        parkingLots.add(parkingLot1);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
//        Car car1 = new Car();
//        Car car2 = new Car();
//        Receipt receipt = parkingBoy.parking(car1);
//        parkingLot1.park(car2);
//        assertThat(parkingBoy.unPark(receipt), is(car1));
//        try {
//            parkingBoy.unPark(receipt);
//            fail("Parking boy should not get the car by wrong receipt");
//        } catch (WrongReceiptException exception) {
//        }
    }

    @Test
    public void should_park_successfully_when_call_park_again_given_a_full_parking_lots_take_out_a_car(){

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car theCar = new Car();
        Receipt receipt = parkingBoy.parking(theCar);
        parkingBoy.unPark(receipt);

        try {
            parkingBoy.parking(new Car());
        } catch (ParkingLotFullException exception) {
            fail("Parking boy should parking successfully");
        }
    }

    @Test
    public void should_get_UUID_of_receipt_when_parking_successfully(){

        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        car.setCarNum("粤H88888");
        try {
            Receipt receipt = parkingLot.park(car);
            assertThat(parkingLot.parkedCars.get(receipt).getCarNum(),
                    is("粤H88888"));
        } catch (ParkingLotFullException exception) {
            fail("Should parking successfully and get UUID of receipt");
        }
    }

}
