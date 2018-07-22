package com.thoughtworks.tdd;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.shell.controller.*;
import com.thoughtworks.tdd.shell.Router;
import com.thoughtworks.tdd.shell.controller.MainPageControllers.*;
import com.thoughtworks.tdd.shell.controller.ManagerPageControllers.*;
import com.thoughtworks.tdd.shell.io.Request;
import com.thoughtworks.tdd.shell.io.Response;

import java.util.ArrayList;
import java.util.Scanner;

public class Root {

    public static Scanner cliInput = new Scanner(System.in);

    public static void main(String[] args) {

        String initRootPath = "root";
        Request request = new Request();
        Router router = initRouter(initRootPath, request);
        router.launch();

        try {
            while (true) {
                String command = cliInput.nextLine();
                request.setCommand(command);
                router.processRequest(request);
            }
        } catch (Exception ex) {
            System.out.println("\n App Exist, cause from route not exist!");
        } finally {
            cliInput.close();
        }
    }

    private static Router initRouter(String currentPage, Request request) {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.setId("001");
        parkingLot1.setName("东门停车场");
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLot2.setId("002");
        parkingLot2.setName("西门停车场");
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy boy = new ParkingBoy(parkingLots);
        Response response = new Response();

        Router router = new Router(currentPage);
        router.registerRoute("root", new RootController(request, response, boy));

        router.registerRoute("root/1", new GoToMainController(request, response, boy));
        router.registerRoute("root/1/1", new GoToParkingController(request, response, boy));
        router.registerRoute("root/1/2", new GoToPickUpController(request, response, boy));
        router.registerRoute("root/1/1/*", new ParkingController(request, response, boy));
        router.registerRoute("root/1/2/*", new PickUpController(request, response, boy));

        router.registerRoute("root/2", new GoToManagerController(request, response, boy));
        router.registerRoute("root/2/1", new ShowDetailController(request, response, boy));
        router.registerRoute("root/2/2", new GoToAddParkingLotController(request, response, boy));
        router.registerRoute("root/2/3", new GoToRemoveParkingLotController(request, response, boy));
        router.registerRoute("root/2/2/*", new AddParkingLotController(request, response, boy));
        router.registerRoute("root/2/3/*", new RemoveParkingLotController(request, response, boy));

        router.registerRoute("root/*", new HandleWrongInputController(request, response, boy));
        router.registerRoute("root/1/*", new HandleWrongInputController(request, response, boy));
        router.registerRoute("root/2/*", new HandleWrongInputController(request, response, boy));

        return router;
    }

}
