package com.thoughtworks.tdd.Controller;

import com.thoughtworks.tdd.View.Request;

public class Router {

    private static final String PARK = "park";
    private static final String UNPARK = "unpark";
    private static final String MAIN = "main";

    private String currentPage;
    private ParkingSystem parkingSys;

    public Router(String currentPage,ParkingSystem parkingSys){
        setCurrentPage(currentPage);
        setParkingSys(parkingSys);
        forwardMainPage();
    }

    public void forwardMainPage(){
        parkingSys.showMainPage();
    }

    public ParkingSystem getParkingSys() { return parkingSys; }
    public void setParkingSys(ParkingSystem parkingSys) { this.parkingSys = parkingSys; }

    public String getCurrentPage() { return currentPage; }
    public void setCurrentPage(String currentPage) { this.currentPage = currentPage; }

    public String handleMainPage(String status,String commands){
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
                break;
        }
        return status;
    }

    public String handleParkPage(){
        parkingSys.park();
        currentPage = MAIN;
        return currentPage;
    }

    public String handleUnparkPage(){
        parkingSys.unpark();
        currentPage = MAIN;
        return currentPage;
    }

    public void handleRequest(Request request){
        switch (currentPage){
            case MAIN:
                currentPage = handleMainPage(currentPage,request.getCommand());
                break;
            case PARK:
                currentPage = handleParkPage();
                forwardMainPage();
                break;
            case UNPARK:
                currentPage = handleUnparkPage();
                forwardMainPage();
                break;
        }
        if(currentPage.equals("isFull")) {
            forwardMainPage();
            currentPage = MAIN;
        }
    }

}
