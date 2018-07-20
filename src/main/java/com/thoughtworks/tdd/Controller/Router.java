package com.thoughtworks.tdd.Controller;

import com.thoughtworks.tdd.View.Request;

public class Router {

    private static final String ROOT = "root";

    private static final String MAIN = "main";
    private static final String PARK = "park";
    private static final String UNPARK = "unpark";

    private static final String MANAGER = "manager";
    private static final String DETAIL = "detail";
    private static final String ADD = "add";
    private static final String REMOVE = "remove";

    private String currentPage;
    private ParkingSystem parkingSys;

    public Router(String currentPage,ParkingSystem parkingSys){
        setCurrentPage(currentPage);
        setParkingSys(parkingSys);
        forwardRootPage();
    }

    public void forwardRootPage(){
        parkingSys.showRootPage();
    }

    public ParkingSystem getParkingSys() { return parkingSys; }
    public void setParkingSys(ParkingSystem parkingSys) { this.parkingSys = parkingSys; }

    public String getCurrentPage() { return currentPage; }
    public void setCurrentPage(String currentPage) { this.currentPage = currentPage; }

    public String handleRootPage(String status,String commands){//处理根主页面
        switch (commands){
            case "1":
                parkingSys.showMainPage();
                status = MAIN;
                break;
            case "2":
                parkingSys.showManagerPage();
                status = MANAGER;
                break;
            default:
                parkingSys.wrongInput();
                forwardRootPage();
                break;
        }
        return status;
    }

    public String handleMainPage(String status,String commands){ //处理停车服务页面
        switch (commands){
            case "1":
                status = parkingSys.showParkPage();
                break;
            case "2":
                parkingSys.showUnparkPage();
                status = UNPARK;
                break;
            default:
                parkingSys.wrongInput();
                forwardRootPage();
                status = ROOT;
                break;
        }
        return status;
    }

    public String handleParkPage(){ //处理停车页面
        parkingSys.park();
        currentPage = ROOT;
        return currentPage;
    }

    public String handleUnparkPage(){//处理取车页面
        parkingSys.unpark();
        currentPage = ROOT;
        return currentPage;
    }

    public String handleManagerPage(String status,String commands){ //处理停车管理页面
        switch (commands){
            case "1":
                parkingSys.showParkingDetail();
                forwardRootPage();
                status = ROOT;
                break;
            case "2":
                parkingSys.showAddParkingLotPage();
                status = ADD;
                break;
            case "3":
                parkingSys.showRemoveParkingLotPage();
                status = REMOVE;
                break;
            default:
                parkingSys.wrongInput();
                forwardRootPage();
                status = ROOT;
                break;
        }
        return status;
    }

    public String handleAddPage(){
        parkingSys.addParkingLot();
//        if(parkingSys.isInputValid()){
        parkingSys.succeedToAddParkingLot();
//        }
        currentPage = ROOT;
        return currentPage;
    }

    public String handleRemovePage(){
        parkingSys.removeParkingLot();
        currentPage = ROOT;
        return currentPage;
    }

    public void handleRequest(Request request){//处理输入请求
        switch (currentPage){
            case ROOT:
                currentPage = handleRootPage(currentPage,request.getCommand());
                break;
            case MAIN:
                currentPage = handleMainPage(currentPage,request.getCommand());
                break;
            case PARK:
                currentPage = handleParkPage();
                forwardRootPage();
                break;
            case UNPARK:
                currentPage = handleUnparkPage();
                forwardRootPage();
                break;
            case MANAGER:
                currentPage = handleManagerPage(currentPage,request.getCommand());
                break;
            case DETAIL:
                currentPage = ROOT;
                forwardRootPage();
                break;
            case ADD:
                currentPage = handleAddPage();
                forwardRootPage();
                break;
            case REMOVE:
                currentPage = handleRemovePage();
                forwardRootPage();
                break;
        }
        if(currentPage.equals("isFull")) {
            forwardRootPage();
            currentPage = ROOT;
        }
    }

}
