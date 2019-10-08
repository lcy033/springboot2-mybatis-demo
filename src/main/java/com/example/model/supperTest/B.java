package com.example.model.supperTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        List<String> list = new ArrayList<>();
        String[] myArray = { "Apple", "Banana", "Orange" };
        List<String> myList = Arrays.asList(myArray);
        System.out.println(myList.getClass());
        System.out.println(list.getClass());
        List<String> list1 = new ArrayList<>(myList);
        System.out.println(list1.getClass());
        System.out.println(Arrays.stream(myArray).collect(Collectors.toList()).getClass());

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
