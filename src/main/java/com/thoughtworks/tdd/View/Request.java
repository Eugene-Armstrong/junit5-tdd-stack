package com.thoughtworks.tdd.View;

public class Request {
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    private String command;
    public Request(){}
    public Request(String command){setCommand(command);}
}
