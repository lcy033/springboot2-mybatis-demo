package com.example.utils;

public class ThreadLocalUtils {

    private static ThreadLocal<String> dataMap = new ThreadLocal<>();

    public static void setData(String data){
        dataMap.set(data);
    }

    public static String getData(){
        String s;
        try {
            s = dataMap.get();
        }catch (Exception e){
            s = dataMap.get();
        }finally {
            dataMap.remove();
        }
        return s;
    }

    public static void delData(){
        dataMap.remove();
    }

}