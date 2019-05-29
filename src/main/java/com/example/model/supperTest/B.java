package com.example.model.supperTest;

/**
 * Created by finup on 2019/5/29.
 */
public class B extends A{

    @Override
    void value() {
        name = "B";
        super.value();
        System.out.println(name);
        System.out.println(super.name);
    }


    public static void main(String[] args) {
        B b = new B();
        b.value();
    }
}
