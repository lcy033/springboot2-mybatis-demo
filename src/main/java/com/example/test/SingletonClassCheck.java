package com.example.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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


    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 1000; i++){
            map.put(String.valueOf(i), String.valueOf(i));
        }

        for (int j = 0; j <= map.entrySet().size(); j ++){
            if (map.get(String.valueOf(j)).hashCode() > 0){
                System.out.println(j);
            }
        }

        System.out.println(map);

    }

}
