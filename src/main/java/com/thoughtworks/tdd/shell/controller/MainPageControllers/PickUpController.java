package com.thoughtworks.tdd.shell.controller.MainPageControllers;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.core.WrongReceiptException;
import com.thoughtworks.tdd.shell.controller.BaseController;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

import java.util.UUID;

public class PickUpController implements BaseController {

    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public PickUpController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        try {
            String reciptID = request.getCommand();

            Car car = parkingBoy.unPark(new Receipt(reciptID));

            if (car == null) {
                response.send("非法小票，无法取出车，请查证后再输！\n");
            } else {
                response.send("车已取出，您的车牌号是: " + car.getCarNum()+"\n");
            }

        } catch (WrongReceiptException ex) {
            response.send("非法小票，无法取出车，请查证后再输！\n");
        } finally {
            return "forward:root";
        }

    }


}
