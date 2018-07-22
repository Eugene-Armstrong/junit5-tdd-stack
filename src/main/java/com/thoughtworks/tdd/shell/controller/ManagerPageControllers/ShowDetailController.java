package com.thoughtworks.tdd.shell.controller.ManagerPageControllers;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.shell.controller.BaseController;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

public class ShowDetailController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public ShowDetailController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        response.send("|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================");
        int sum = 0,parkedSum = 0;
        for(ParkingLot parkingLot:parkingBoy.getParkingLots()){
            int subSum = parkingLot.getSize(), parkedNum = parkingLot.getParkedCars().size();
            sum+=subSum;
            parkedSum+=parkedNum;
            response.send("|"+parkingLot.getId()+
                    "|"+parkingLot.getName()+
                    "|"+subSum+ "(个)|"+parkedNum+"(辆)|"+(subSum-parkedNum)+"(个)|");
        }
        response.send("\n总车位："+sum+"(个)\n" +
                "停车总量："+parkedSum+"(辆)\n" +
                "总剩余车位："+(sum-parkedSum)+"(个)\n");
        return "forward:root";
    }
}
