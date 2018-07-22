package com.thoughtworks.tdd.shell.controller.MainPageControllers;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.shell.controller.BaseController;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

public class ParkingController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public ParkingController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        String cardNum = request.getCommand();

        Receipt receipt = parkingBoy.parking(new Car(cardNum));

        response.send("停车成功，您的小票是：\n" +
                receipt.getReceiptUUID()+"\n");
        return "forward:root";
    }
}
