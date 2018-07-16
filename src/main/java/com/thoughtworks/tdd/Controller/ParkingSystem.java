package com.thoughtworks.tdd.Controller;

import com.thoughtworks.tdd.Exception.WrongReceiptException;
import com.thoughtworks.tdd.Model.Car;
import com.thoughtworks.tdd.Model.ParkingBoy;
import com.thoughtworks.tdd.Model.Receipt;
import com.thoughtworks.tdd.View.Request;
import com.thoughtworks.tdd.View.Response;

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



    public void showMainPage(){ response.send("1. 停车\n2. 取车\n请输入您要进行的操作："); }

    public void wrongInput(){ response.send("非法指令，请查证后再输!\n"); }

    public String showParkPage(){
        if(!parkingBoy.isParkingLotsFull()){
            response.send("请输入车牌号：");
            return "park";
        }else{
            response.send("车已停满，请晚点再来！");
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
}
