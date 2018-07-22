package com.thoughtworks.tdd.shell.controller.MainPageControllers;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.shell.controller.BaseController;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

public class GoToParkingController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public GoToParkingController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {

        String forwardPath = "";
        if (parkingBoy.isParkingLotsFull()) {
            response.send("车已停满，请晚点再来！\n");
            forwardPath = "forward:root";
        } else {
            response.send("请输入车牌号:");
        }
        return forwardPath;
    }
}
