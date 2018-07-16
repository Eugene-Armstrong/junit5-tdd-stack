package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Controller.ParkingSystem;
import com.thoughtworks.tdd.Controller.Router;
import com.thoughtworks.tdd.Model.ParkingBoy;
import com.thoughtworks.tdd.Model.ParkingLot;
import com.thoughtworks.tdd.View.Request;
import com.thoughtworks.tdd.View.Response;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Request request = new Request();
        Response response = new Response();

        ParkingSystem parkingSys = new ParkingSystem(request,response,parkingBoy);
        String currentPage = "main";
        Router router = new Router(currentPage,parkingSys);

        try {
            while(true){
                String command = userInput.next();
                request.setCommand(command);
                router.handleRequest(request);
            }
        }catch (Exception e){
            System.out.println("Exception catched");
        }finally {
            userInput.close();
        }
    }
}
