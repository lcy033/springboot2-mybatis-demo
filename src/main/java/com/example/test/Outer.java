package com.example.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Outer {

    private int a = 1;
    private int b = 2;

    private class Inner {
        private int a = 3;
        public void method(){
            System.out.println(a+b);
        }

    }

    public static void main(String[] args) {

    }
}
