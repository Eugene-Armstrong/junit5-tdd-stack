package com.thoughtworks.tdd.shell.controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

public class HandleWrongInputController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public HandleWrongInputController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        response.send("非法指令，请查证后再输!\n");
        return "forward:root";
    }
}
