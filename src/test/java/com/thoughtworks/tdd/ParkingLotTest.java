package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class ParkingLotTest {

    @Test
    public void should_park_successfully_given_parking_lot_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        try {
            parkingLot.park(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }

    @Test
    public void should_park_failed_given_parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);
        try {
            parkingLot.park(new Car());
            fail("should park successfully");
        } catch (ParkingLotFullException exception) { }
    }

    @Test
    public void should_get_specific_NewCar_when_call_unPark_given_receipt_is_right(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);
        assertThat(parkingLot.unPark(receipt), is(theCar));
    }

    @Test
    public void should_not_get_specific_NewCar_when_call_unPark_given_receipt_is_wrong(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);

        Receipt anotherReceipt = new Receipt();

        assertThat(parkingLot.unPark(anotherReceipt), not(theCar));
    }

    @Test
    public void should_be_true_when_call_isFull_given_parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);

        assertThat(parkingLot.isFull(), is(true));
    }

    @Test
    public void should_be_false_when_call_isFull_given_parking_lot_is_not_full(){
        ParkingLot parkingLot = new ParkingLot(1);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_NewCar(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_park_successfully_when_call_park_again_given_a_full_parking_lot_take_out_a_NewCar(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        try {
            parkingLot.park(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }

    public static void shoudNotThrowException(){
        //throw new ParkingLotFullException();
    }

    @Test
    public void should_fail(){

        System.out.println("Hello I am OK");
        try {
            shoudNotThrowException();
        } catch (ParkingLotFullException e) {
            System.out.println("Hello I am in catch");
            fail("it failed");
        }
        System.out.println("Hello I am called");

    }

    @Test
    public void should_return_false_when_given_parking_lots_are_not_full() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        ParkingLot parkingLot1 = mock(ParkingLot.class);
//        when(parkingLot1.getSize()).thenReturn(0);
//        ParkingLot parkingLot2 = mock(ParkingLot.class);
//        when(parkingLot2.getSize()).thenReturn(1);
//        ParkingLot parkingLot3 = mock(ParkingLot.class);
//        when(parkingLot3.getSize()).thenReturn(0);
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(0);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

//        verify(parkingBoy).isParkingLotsFull();

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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        try {
            parkingBoy.park(parkingLots);
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }

    @Test
    public void should_park_not_successfully_when_given_parking_lots_are_full() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        try {
            parkingBoy.park(parkingLots);
            fail("should park successfully");
        } catch (ParkingLotFullException exception) {
        }
    }

    @Test
    public void should_get_specific_NewCar_when_call_unPark_from_parking_boy_given_receipt_is_right(){
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Receipt receipt = parkingBoy.park(parkingLots);

        assertThat(parkingBoy.unPark(receipt), is(parkingLot1.parkedNewCars.get(receipt)));
    }

    @Test
    public void should_get_specific_NewCar_when_call_unPark_from_parking_boy_given_receipt_is_wrong(){
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car theCar = new Car();
        Receipt receipt = parkingBoy.park(parkingLots);

        assertThat(parkingBoy.unPark(receipt), not(theCar));
    }

    @Test
    public void should_be_false_when_call_isParkingLotsFull_given_full_parking_lots_take_out_a_NewCar(){
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Receipt receipt = parkingBoy.park(parkingLots);
        parkingBoy.unPark(receipt);

        assertThat(parkingBoy.isParkingLotsFull(), is(false));
    }

//    @Test
//    public void should_get_UUID_of_receipt_when_parking_successfully(){
//        ParkingLot newParkingLot = new ParkingLot(1);
//        try {
//            newParkingLot.park(new Car());
//        } catch (ParkingLotFullException exception) {
//            fail("should park successfully");
//        }
//    }

}
