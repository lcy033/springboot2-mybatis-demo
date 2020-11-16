package com.example.test.chushihua;

public class Son extends Father {

    static {
        System.out.println("6");
    }

    private int i = test();

    private static int j = method();

    Son() {
        System.out.println("7");
    }

    {
        System.out.println("8");
    }

    private int test() {
        System.out.println("9");
        return 1;
    }

    private static int method() {
        System.out.println("10");
        return 1;
    }

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println("----------");
        Son son1 = new Son();
    }
}