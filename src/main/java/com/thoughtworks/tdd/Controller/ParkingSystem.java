package com.thoughtworks.tdd.Controller;

import com.thoughtworks.tdd.Exception.WrongReceiptException;
import com.thoughtworks.tdd.Model.Car;
import com.thoughtworks.tdd.Model.ParkingBoy;
import com.thoughtworks.tdd.Model.ParkingLot;
import com.thoughtworks.tdd.Model.Receipt;
import com.thoughtworks.tdd.View.Request;
import com.thoughtworks.tdd.View.Response;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class ParkingSystem {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    private Receipt receipt;

    public ParkingSystem(Request request, Response response, ParkingBoy parkingBoy){
        setRequest(request);
        setResponse(response);
        setParkingBoy(parkingBoy);
    }

    public ParkingBoy getParkingBoy() { return parkingBoy; }
    public void setParkingBoy(ParkingBoy parkingBoy) { this.parkingBoy = parkingBoy; }
    public Response getResponse() { return response; }
    public void setResponse(Response response) { this.response = response; }
    public Request getRequest() { return request; }
    public void setRequest(Request request) { this.request = request; }


    public void showRootPage(){
        response.send("1.停车服务\n2.停车场管理\n请输入您要进入的页面：");
    }

    public void showMainPage(){ response.send("1. 停车\n2. 取车\n请输入您要进行的操作："); }

    public void wrongInput(){ response.send("非法指令，请查证后再输!\n"); }

    public String showParkPage(){
        if(!parkingBoy.isParkingLotsFull()){
            response.send("请输入车牌号：");
            return "park";
        }else{
            response.send("车已停满，请晚点再来！\n");
            return "isFull";
        }
    }

    public void park(){
        String cardNum = request.getCommand();
        receipt = parkingBoy.parking(new Car(cardNum));
        showParkSuccessfully(receipt);
    }

    public void showParkSuccessfully(Receipt receipt){ response.send("停车成功，您的小票是：\n" + receipt.getReceiptUUID()+"\n"); }

    public void showUnparkPage(){ response.send("请输入小票编号："); }

    public void unpark(){
        String receiptNo = request.getCommand();
        try {
            Receipt receipt1 = new Receipt(receiptNo);
            Car newCar = parkingBoy.unPark(receipt1);
            showUnparkSuccessfully(newCar.getCarNum());
        }catch (WrongReceiptException e){
            showWrongReceipt();
        }
    }

    public void showUnparkSuccessfully(String carNum){
        response.send("车已取出，您的车牌号是: "+carNum+"\n");
    }

    public void showWrongReceipt(){ response.send("非法小票，无法取出车，请查证后再输！\n"); }


    public void showManagerPage(){
        response.send("1.查看停车场详情\n2.添加停车场\n3.删除停车场");
    }

    public void showParkingDetail(){
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
    }

    public void showAddParkingLotPage(){
        response.send("请输入你要添加的停车场信息（格式为：名称，车位）：");
    }

//    public boolean isInputValid(){
//        try{
//            String[] test = request.getCommand().split("，");
//            return true;
//        }catch (Exception e){
//            System.out.println("参数有误，请按格式重新输入。\n");
//            return false;
//        }
//    }

    public void addParkingLot(){
//        if(isInputValid()){
        String[] newInfo = request.getCommand().split("，");
        String name = newInfo[0];
        int size = parseInt(newInfo[1]);
        ParkingLot newParkingLot = new ParkingLot(size);
        newParkingLot.setId("003");
        newParkingLot.setName(name);
        parkingBoy.getParkingLots().add(newParkingLot);
//        }
    }

    public void succeedToAddParkingLot(){
        response.send("停车场添加成功！\n");
    }

    public void showRemoveParkingLotPage(){
        response.send("请输入需要删除的被管理停车场ID：");
    }

    public void removeParkingLot(){
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
    }

    public void succeedToRemovePrakingLot(){
        response.send("停车场删除成功！");
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
