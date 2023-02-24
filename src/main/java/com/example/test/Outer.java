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
        Outer.getString();
//        System.out.println(Outer.getString());
    }

    public static String getString() {
        try {
            System.out.println("3");
        } catch (Exception e) {
            System.out.println("2");
        } finally {
            System.out.println("1");
        }
        return "1";
    }
}
