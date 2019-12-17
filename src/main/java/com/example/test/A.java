package com.example.test;

/**
 * Created by finup on 2019/12/13.
 */
public class A {
    public A() {
        System.out.println("A");
    }

    static {
        System.out.println("A+");
    }

    void print() {
        System.out.println("A++");
    }
}
