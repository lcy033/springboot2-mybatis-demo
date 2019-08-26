package com.example.test;

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

}
