package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FizzBuzzWhizzTest {

    @Test
    public void should_return_1_when_call_FizzBuzzWhizz_given_input_is_1(){
        //given
        int number =1;
        FizzBuzzWhizz FizzBuzzWhizz = new FizzBuzzWhizz();
        //when
        String result = FizzBuzzWhizz.FizzBuzzWhizzWithNumber(number);
        //then
        assertThat(result ,is("1"));
    }

    @Test
    public void should_return_2_when_call_FizzBuzzWhizz_given_input_is_2(){
        //given
        int number =2;
        FizzBuzzWhizz FizzBuzzWhizz = new FizzBuzzWhizz();
        //when
        String result = FizzBuzzWhizz.FizzBuzzWhizzWithNumber(number);
        //then
        assertThat(result ,is("2"));
    }

    @Test
    public void should_return_Fizz_when_call_FizzBuzzWhizz_given_input_is_3(){
        //given
        int number =3;
        FizzBuzzWhizz FizzBuzzWhizz = new FizzBuzzWhizz();
        //when
        String result = FizzBuzzWhizz.FizzBuzzWhizzWithNumber(number);
        //then
        assertThat(result ,is("Fizz"));
    }

    @Test
    public void should_return_Fizz_when_call_FizzBuzzWhizz_given_input_is_6(){
        //given
        int number =6;
        FizzBuzzWhizz FizzBuzzWhizz = new FizzBuzzWhizz();
        //when
        String result = FizzBuzzWhizz.FizzBuzzWhizzWithNumber(number);
        //then
        assertThat(result ,is("Fizz"));
    }

    @Test
    public void should_return_Buzz_when_call_FizzBuzzWhizz_given_input_is_5(){
        //given
        int number =5;
        FizzBuzzWhizz FizzBuzzWhizz = new FizzBuzzWhizz();
        //when
        String result = FizzBuzzWhizz.FizzBuzzWhizzWithNumber(number);
        //then
        assertThat(result ,is("Buzz"));
    }

    @Test
    public void should_return_FizzBuzzWhizz_when_call_FizzBuzzWhizz_given_input_is_15(){
        //given
        int number =15;
        FizzBuzzWhizz FizzBuzzWhizz = new FizzBuzzWhizz();
        //when
        String result = FizzBuzzWhizz.FizzBuzzWhizzWithNumber(number);
        //then
        assertThat(result ,is("FizzBuzz"));
    }
    @Test
    public void should_return_Whizz_when_call_FizzBuzzWhizz_given_input_is_7(){
        //given
        int number =7;
        FizzBuzzWhizz FizzBuzzWhizz = new FizzBuzzWhizz();
        //when
        String result = FizzBuzzWhizz.FizzBuzzWhizzWithNumber(number);
        //then
        assertThat(result ,is("Whizz"));
    }
    @Test
    public void should_return_FizzWhizz_when_call_FizzBuzzWhizz_given_input_is_21(){
        //given
        int number =21;
        FizzBuzzWhizz FizzBuzzWhizz = new FizzBuzzWhizz();
        //when
        String result = FizzBuzzWhizz.FizzBuzzWhizzWithNumber(number);
        //then
        assertThat(result ,is("FizzWhizz"));
    }

    @Test
    public void should_return_Fizz_when_call_FizzBuzzWhizz_given_input_is_35(){
        //given
        int number =35;
        FizzBuzzWhizz FizzBuzzWhizz = new FizzBuzzWhizz();
        //when
        String result = FizzBuzzWhizz.FizzBuzzWhizzWithNumber(number);
        //then
        assertThat(result ,is("Fizz"));
    }

    @Test
    public void should_return_Fizz_when_call_FizzBuzzWhizz_given_input_is_13(){
        //given
        int number =13;
        FizzBuzzWhizz FizzBuzzWhizz = new FizzBuzzWhizz();
        //when
        String result = FizzBuzzWhizz.FizzBuzzWhizzWithNumber(number);
        //then
        assertThat(result ,is("Fizz"));
    }
}
