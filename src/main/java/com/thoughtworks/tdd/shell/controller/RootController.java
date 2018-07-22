package com.thoughtworks.tdd.shell.controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

public class RootController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public RootController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        response.send("1.停车服务\n2.停车场管理\n请输入您要进入的页面：");
        return "";
    }
}
