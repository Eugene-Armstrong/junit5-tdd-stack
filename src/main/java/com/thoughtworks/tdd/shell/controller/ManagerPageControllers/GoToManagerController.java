package com.thoughtworks.tdd.shell.controller.ManagerPageControllers;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.shell.controller.BaseController;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

public class GoToManagerController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public GoToManagerController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        response.send("1.查看停车场详情\n2.添加停车场\n3.删除停车场");
        return "";
    }
}
