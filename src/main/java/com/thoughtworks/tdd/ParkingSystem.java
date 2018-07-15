package com.thoughtworks.tdd;

import java.util.Objects;
import java.util.Scanner;

public class ParkingSystem {
    public String input;
    public Receipt curReceipt;

    public Receipt getCurReceipt() {
        return curReceipt;
    }

    public void setCurReceipt(Receipt curReceipt) {
        this.curReceipt = curReceipt;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }


    public boolean isInputValid(){
        System.out.println("1. 停车\n2. 取车\n请输入您要进行的操作：");
        Scanner scanOperation = new Scanner(System.in);
        input = scanOperation.next();
        boolean result = input.equals("1")||input.equals("2");
        if(!result){
            System.out.print("非法指令，请查证后再输!\n\n");
        }
        return result;
    }

    public void handleParkingInput(ParkingBoy parkingBoy){
        if (!parkingBoy.isParkingLotsFull()) {
            Car car = new Car("");
            System.out.println("请输入车牌号：");
            Scanner scanCarNum = new Scanner(System.in);
            String carNum = scanCarNum.next();
            car.setCarNum(carNum);
            curReceipt = parkingBoy.parking(car);
            System.out.print("停车成功，您的小票是：\n" + curReceipt.getReceiptUUID() + "\n\n");

        } else {
            System.out.print("车已停满，请晚点再来!\n\n");
            throw new ParkingLotFullException();
        }
    }

    public void handleUnparkInput(ParkingBoy parkingBoy){
        if (curReceipt != null){
            System.out.println("请输入小票编号：");
            Scanner scanReceipt = new Scanner(System.in);
            String receiptNo = scanReceipt.next();
            if(receiptNo.equals(curReceipt.getReceiptUUID())){
                Car car = parkingBoy.unPark(curReceipt);
                curReceipt = null;
                System.out.print("车已取出，您的车牌号是: "+car.getCarNum()+"\n\n");
            }else {
                System.out.print("非法小票，无法取出车，请查证后再输!\n\n");
//          System.out.println("小票编号为:"+receiptNo+"\n");
            }
        }else{
            System.out.print("非法小票，无法取出车，请查证后再输!\n\n");
        }
    }

    public void runSys(ParkingBoy parkingBoy){
        while (true){
            if (isInputValid()){
                switch (getInput()){
                    case "1":
                        handleParkingInput(parkingBoy);
                        break;
                    case "2":
                        handleUnparkInput(parkingBoy);
                        break;
                }
            }
        }
    }

}
