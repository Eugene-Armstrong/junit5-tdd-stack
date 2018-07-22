package com.thoughtworks.tdd.shell.controller.ManagerPageControllers;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.shell.controller.BaseController;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

import static java.lang.Integer.parseInt;

public class AddParkingLotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public AddParkingLotController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        try{
            String[] newInfo = request.getCommand().split("，");
            String name = newInfo[0];
            int size = parseInt(newInfo[1]);
            ParkingLot newParkingLot = new ParkingLot(size);
            newParkingLot.setId("003");
            newParkingLot.setName(name);
            parkingBoy.getParkingLots().add(newParkingLot);
            response.send("停车场添加成功！\n");
            return "forward:root";
        }catch (Exception e){
            System.out.println("参数有误，请按格式重新输入。");
            return "forward:root/2/2";
        }
    }
}
