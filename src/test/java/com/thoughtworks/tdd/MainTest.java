package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Controller.ParkingSystem;
import com.thoughtworks.tdd.Exception.WrongReceiptException;
import com.thoughtworks.tdd.Model.Car;
import com.thoughtworks.tdd.Model.ParkingBoy;
import com.thoughtworks.tdd.Model.Receipt;
import com.thoughtworks.tdd.View.Request;
import com.thoughtworks.tdd.View.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    public void should_print_please_input_carNum_when_call_showParkPage_given_not_parking_lots_not_full(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.isParkingLotsFull()).thenReturn(false);
        ParkingSystem parkingSys = new ParkingSystem(request,response,parkingBoy);
        parkingSys.showParkPage();
        verify(response).send("请输入车牌号：");
    }

    @Test
    public void should_print_parklots_are_full_when_call_showParkPage_given_input_full_parkinglots(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.isParkingLotsFull()).thenReturn(true);
        ParkingSystem parkingSys = new ParkingSystem(request,response,parkingBoy);
        parkingSys.showParkPage();
        verify(response).send("车已停满，请晚点再来！");
    }

    @Test
    public void should_print_park_successfully_and_receipt_when_call_park(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        Receipt receipt = new Receipt("23f315c1-4eca-4ea9-9433-6349af7763dd");
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.parking(Mockito.any())).thenReturn(receipt);
        ParkingSystem parkingSys = new ParkingSystem(request,response,parkingBoy);
        parkingSys.park();
        verify(response).send("停车成功，您的小票是：\n" + receipt.getReceiptUUID()+"\n");
    }

    @Test
    public void should_print_car_has_been_token_successfully_and_carNum_when_call_unpark_given_right_receipt(){
        Car car = new Car("粤HDT386");
        Request request = mock(Request.class);
        when(request.getCommand()).thenReturn("fef8e384-8738-11e8-adc0-fa7ae01bbebc");
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.unPark(Mockito.any())).thenReturn(car);
        ParkingSystem parkingSys = new ParkingSystem(request,response,parkingBoy);
        parkingSys.unpark();
        verify(response).send("车已取出，您的车牌号是: "+car.getCarNum() + "\n");
    }

    @Test
    public void should_print_car_can_not_be_token_and_when_call_unpark_given_wrong_receipt(){
        Car car = new Car("粤HDT386");
        Request request = mock(Request.class);
        when(request.getCommand()).thenReturn("23f315c1-4eca-4ea9-9433-6349af7763dd");
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.unPark(Mockito.any())).thenThrow(new WrongReceiptException());
        ParkingSystem parkingSys = new ParkingSystem(request,response,parkingBoy);
        parkingSys.unpark();
        verify(response).send("非法小票，无法取出车，请查证后再输！\n");
    }
}
