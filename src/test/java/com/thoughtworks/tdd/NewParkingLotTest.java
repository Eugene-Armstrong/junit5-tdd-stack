package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;


public class NewParkingLotTest {

    @Test
    public void should_park_successfully_given_parking_lot_is_not_full() {
        NewParkingLot newParkingLot = new NewParkingLot(1);
        try {
            newParkingLot.park(new NewCar());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }

    @Test
    public void should_park_failed_given_parking_lot_is_full(){
        NewParkingLot newParkingLot = new NewParkingLot(0);
        try {
            newParkingLot.park(new NewCar());
            fail("should park successfully");
        } catch (ParkingLotFullException exception) { }
    }

    @Test
    public void should_get_specific_NewCar_when_call_unPark_given_receipt_is_right(){
        NewParkingLot newParkingLot = new NewParkingLot(1);
        NewCar theNewCar = new NewCar();
        Receipt receipt = newParkingLot.park(theNewCar);
        assertThat(newParkingLot.unPark(receipt), is(theNewCar));
    }

    @Test
    public void should_not_get_specific_NewCar_when_call_unPark_given_receipt_is_wrong(){
        NewParkingLot newParkingLot = new NewParkingLot(1);

        NewCar theNewCar = new NewCar();
        Receipt receipt = newParkingLot.park(theNewCar);

        Receipt anotherReceipt = new Receipt();

        assertThat(newParkingLot.unPark(anotherReceipt), not(theNewCar));
    }

    @Test
    public void should_be_true_when_call_isFull_given_parking_lot_is_full(){
        NewParkingLot newParkingLot = new NewParkingLot(0);

        assertThat(newParkingLot.isFull(), is(true));
    }

    @Test
    public void should_be_false_when_call_isFull_given_parking_lot_is_not_full(){
        NewParkingLot newParkingLot = new NewParkingLot(1);

        assertThat(newParkingLot.isFull(), is(false));
    }

    @Test
    public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_NewCar(){
        NewParkingLot newParkingLot = new NewParkingLot(1);

        NewCar theNewCar = new NewCar();
        Receipt receipt = newParkingLot.park(theNewCar);
        newParkingLot.unPark(receipt);

        assertThat(newParkingLot.isFull(), is(false));
    }

    @Test
    public void should_park_successfully_when_call_park_again_given_a_full_parking_lot_take_out_a_NewCar(){
        NewParkingLot newParkingLot = new NewParkingLot(1);

        NewCar theNewCar = new NewCar();
        Receipt receipt = newParkingLot.park(theNewCar);
        newParkingLot.unPark(receipt);

        try {
            newParkingLot.park(new NewCar());
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
}
