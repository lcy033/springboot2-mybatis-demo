package com.example;

import com.example.vo.GspMenuVo;

public class Hellow {

    public static void main(String[] args) {

//        GspMenuVo vo = new GspMenuVo();
//        int x ;
//        int y ;
//        for (x = 0; x < 36; x++) {
//            for (y = 0; y < 36; y++) {
//                String z = x + "" + y;
//                if (x + y + Integer.parseInt(z) == 36){
//                    System.out.println("x:"+x);
//                    System.out.println("y:"+y);
//                    break;
//                }
//            }
//        }

        System.out.println(dsfs());
    }

    private static String dsfs(){
        try {
            System.out.println(1);
            String s = "dfds";
            Integer.parseInt(s);
            return "1";
        }catch (Exception e){
            System.out.println(2);
            return "2";
        }finally {
            System.out.println(3);
//            return "3";
        }
    }

}
