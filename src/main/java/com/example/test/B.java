package com.example.test;

/**
 * Created by finup on 2019/12/13.
 */
public class B extends A {
    public B() {
        System.out.println("B");
    }

    static {
        System.out.println("B+");
    }

    @Override
    void print() {
        System.out.println("B++");
    }

    public static void main(String[] args) {
        B b = new B();
        b.print();
    }
}
