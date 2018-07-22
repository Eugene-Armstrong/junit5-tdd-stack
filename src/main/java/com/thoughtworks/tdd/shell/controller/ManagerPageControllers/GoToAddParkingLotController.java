package com.thoughtworks.tdd.shell.controller.ManagerPageControllers;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.shell.controller.BaseController;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

public class GoToAddParkingLotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public GoToAddParkingLotController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        response.send("请输入你要添加的停车场信息（格式为：名称，车位）：");
        return "";
    }
}
