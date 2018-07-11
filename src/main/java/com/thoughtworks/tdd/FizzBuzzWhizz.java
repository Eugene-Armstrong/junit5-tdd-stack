package com.thoughtworks.tdd;

public class FizzBuzzWhizz {
    public String FizzBuzzWhizzWithNumber(int number) {
        String result = "";
        if(String.valueOf(number).indexOf('3')!=-1){
            return "Fizz";
        }
        if(number % 3 == 0){
            result += "Fizz";
        }
        if(number % 5 == 0){
            result += "Buzz";
        }
        if(number % 7 == 0){
            result += "Whizz";
        }
        return result.equals("")?String.valueOf(number):result;
    }
}
