package com.thoughtworks.tdd;

import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        while (true){
            System.out.println("1. 停车\n2. 取车\n请输入您要进行的操作：");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if (!input.equals("1")&&!input.equals("2")){
                System.out.println("非法指令，请查证后再输!\n");
            }else{
//                String str = input.equals("1")?"您要停车\n":"您要取车\n";
//                System.out.println(str);
                //TODO

            }
        }

    }
}
