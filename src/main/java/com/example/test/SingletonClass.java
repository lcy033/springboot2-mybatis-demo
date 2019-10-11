package com.example.test;

import java.util.*;

/**
 * Created by finup on 2019/8/26.
 */
public class SingletonClass {

    private static SingletonClass singletonClass;

    private SingletonClass(){

    }

    public static SingletonClass getSingletonClass(){
        if (singletonClass == null){
            singletonClass = new SingletonClass();
        }
        return singletonClass;
    }


    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        System.out.println(names);
        Collections.sort(names);
        System.out.println(names);
        Collections.sort(names);
    }

}
