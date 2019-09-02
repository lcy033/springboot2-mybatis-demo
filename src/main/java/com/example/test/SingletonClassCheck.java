package com.example.test;

/**
 * Created by finup on 2019/8/26.
 */
public class SingletonClassCheck {

    private volatile static SingletonClassCheck singletonClass;

    private SingletonClassCheck(){

    }

    public static SingletonClassCheck getSingletonClass(){
        if (singletonClass == null){
            synchronized (SingletonClassCheck.class){
                if (singletonClass == null){
                    singletonClass = new SingletonClassCheck();
                }
            }
        }
        return singletonClass;
    }

}
