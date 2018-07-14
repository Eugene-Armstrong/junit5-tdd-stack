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
    public void should_return_false_when_given_parking_lots_are_not_full() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        assertThat(parkingBoy.isParkingLotsFull(), is(false));
    }

    @Test
    public void should_return_true_when_given_parking_lots_are_full() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        assertThat(parkingBoy.isParkingLotsFull(), is(true));
    }

    @Test
    public void should_park_successfully_when_given_parking_lots_are_not_full() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        try {
            parkingBoy.park(car);
        } catch (ParkingLotFullException exception) {
            fail("Parking boy should park successfully");
        }
    }

    @Test
    public void should_park_not_successfully_when_given_parking_lots_are_full() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        try {
            parkingBoy.park(car);
            fail("Parking boy should park successfully");
        } catch (ParkingLotFullException exception) {
        }
    }

    @Test
    public void should_get_specific_car_when_call_unPark_from_parking_boy_given_receipt_is_right(){

        ParkingBoy parkingBoy = mock(ParkingBoy.class);

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        Receipt receipt = parkingBoy.park(car);

        when(parkingBoy.unPark(receipt)).thenReturn(parkingLot1.parkedNewCars.get(receipt));

        verify(parkingBoy).unPark(receipt);

//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        ParkingLot parkingLot1 = new ParkingLot(1);
//        parkingLots.add(parkingLot1);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
//        Car car = new Car();
//        Receipt receipt = parkingBoy.park(car);
//
//        assertThat(parkingBoy.unPark(receipt), is(parkingLot1.parkedNewCars.get(receipt)));
    }

    @Test
    public void should_not_get_specific_car_when_call_unPark_from_parking_boy_given_receipt_is_wrong(){
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(2);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        Receipt receipt = parkingBoy.park(car1);
        parkingLot1.park(car2);
        assertThat(parkingBoy.unPark(receipt), is(car1));
        assertThat(parkingBoy.unPark(receipt), not(car2));
    }

    @Test
    public void should_be_false_when_call_isParkingLotsFull_given_full_parking_lots_take_out_a_car(){
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car1 = new Car();
        Receipt receipt = parkingBoy.park(car1);
        parkingBoy.unPark(receipt);

        assertThat(parkingBoy.isParkingLotsFull(), is(false));
    }

    @Test
    public void should_park_successfully_when_call_park_again_given_a_full_parking_lots_take_out_a_car(){
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car theCar = new Car();
        Receipt receipt = parkingBoy.park(theCar);
        parkingBoy.unPark(receipt);

        try {
            parkingBoy.park(new Car());
        } catch (ParkingLotFullException exception) {
            fail("Parking boy should park successfully");
        }
    }

    @Test
    public void should_get_UUID_of_receipt_when_parking_successfully(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        car.setCarNum("粤H88888");
        try {
            Receipt receipt = parkingLot.park(car);
            assertThat(parkingLot.parkedNewCars.get(receipt).getCarNum(),
                    is("粤H88888"));
        } catch (ParkingLotFullException exception) {
            fail("Should park successfully and get UUID of receipt");
        }
    }

}
