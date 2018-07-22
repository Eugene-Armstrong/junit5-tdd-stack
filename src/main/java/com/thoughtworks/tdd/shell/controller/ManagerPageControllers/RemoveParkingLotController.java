package com.thoughtworks.tdd.shell.controller.ManagerPageControllers;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.shell.controller.BaseController;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

import java.util.ArrayList;

public class RemoveParkingLotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public RemoveParkingLotController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        String parkingLotID = request.getCommand();
        boolean isNotExist = true;
        ArrayList<ParkingLot> parkingList = parkingBoy.getParkingLots();
        for(int i=0;i<parkingList.size();i++){
            if(parkingLotID.equals(parkingList.get(i).getId())){//存在
                if(parkingList.get(i).getParkedCars().size()==0){//没车
                    parkingList.remove(i);//删除操作
                    succeedToRemovePrakingLot();
                }else{//有车
                    failToRemovePrakingLot();
                    showReason2Page();
                }
                isNotExist = false;
                break;
            }
        }
        if(isNotExist){
            failToRemovePrakingLot();
            showReason1Page();
        }
        return "forward:root";
    }

    public void succeedToRemovePrakingLot(){
        response.send("停车场删除成功！\n");
    }

    public void failToRemovePrakingLot(){
        response.send("停车场删除失败，原因：");
    }

    public void showReason1Page(){
        response.send("此停车场不存在！\n");
    }

    public void showReason2Page(){
        response.send("此停车场中，依然停有汽车，无法删除！\n");
    }
}
