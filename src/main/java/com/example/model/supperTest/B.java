package com.example.model.supperTest;

import java.util.Scanner;

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

        Scanner input = new Scanner(System.in);
        String s = input.nextLine();

        while (!s.equals("yes")) {
            System.out.println("输入错误");
            s = input.nextLine();
        }

        System.out.println("正确");
        input.close();
    }

}
