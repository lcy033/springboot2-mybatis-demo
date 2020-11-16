package com.example.test.chushihua;

public class Father {
    {
        System.out.println("3");
    }

    private int i = test();
    private static int j = method();

    static {
        System.out.println("1");
    }

    Father() {
        System.out.println("2");
    }

    private int test() {
        System.out.println("4");
        return 1;
    }

    private static int method() {
        System.out.println("5");
        return 1;
    }

    public static void main(String[] args) {
        Father father = new Father();
    }
}
