package com.thoughtworks.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.*;

public class MainTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String sysOutput() {
        return outContent.toString();
    }
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void should_return_false_when_call_isInputValid_given_not_1or2(){
        String input ="3";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        ParkingSystem parkingSys = new ParkingSystem();
        assertEquals(parkingSys.isInputValid(),false);
    }

    @Test
    public void should_return_true_when_call_isInputValid_given_input_is_1or2(){
        String input1 ="1";
        String input2 ="2";
        InputStream inputStream1 = new ByteArrayInputStream(input1.getBytes());
        InputStream inputStream2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(inputStream1);
        ParkingSystem parkingSys = new ParkingSystem();
        assertEquals(parkingSys.isInputValid(),true);
        System.setIn(inputStream2);
        assertEquals(parkingSys.isInputValid(),true);
    }

    @Test
    public void should_park_successfully_when_call_handleParkingInput_given_parking_lots_not_full_and_input_is_1(){
        String inputOperation ="1";
        InputStream inputStream = new ByteArrayInputStream(inputOperation.getBytes());
        System.setIn(inputStream);

        String carNo = "XXXXX";
        InputStream carNoStream = new ByteArrayInputStream(carNo.getBytes());
        System.setIn(carNoStream);

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        ParkingSystem parkingSys = new ParkingSystem();
        parkingSys.handleParkingInput(parkingBoy);

        assertEquals(sysOutput().contains(parkingSys.getCurReceipt().getReceiptUUID()),true);
    }

    @Test
    public void should_not_park_successfully_when_call_handleParkingInput_given_parking_lots_full_and_input_is_1(){
        String inputOperation ="1";
        InputStream inputStream = new ByteArrayInputStream(inputOperation.getBytes());
        System.setIn(inputStream);

        String carNo = "XXXXX";
        InputStream carNoStream = new ByteArrayInputStream(carNo.getBytes());
        System.setIn(carNoStream);

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(0);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        ParkingSystem parkingSys = new ParkingSystem();
        try{
            parkingSys.handleParkingInput(parkingBoy);
            fail("Should not park successfully!");
        }catch (ParkingLotFullException e){
        }

    }

}
