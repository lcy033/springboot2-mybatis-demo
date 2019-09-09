package com.example.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by finup on 2019/9/9.
 */
public class MergeGold {

    public static double mergeGold(List<Double> weight, int m){
        if (weight.isEmpty()){
            return 0;
        }
        double a = 0.1;//大于等于10 金额
        double b = 0.005;//超过10个每块金额
        int c = weight.size() / m;//分组
        int d = weight.size() % m;//余数
        int e = 0;//大于10块的个数
        boolean f = d > 10;//d是否大于10

        if (m <= 10){
            if (d != 0){
                c = c + 1;
            }
            return a * c;
        }else {
            e = (m - 10) * c;
            if (f){
                c = d / 10 + c;
                d = d % 10;
            }
            e = e + d;

            return a * c + b * e;
        }

    }

    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        list.add(1d);
        System.out.println(MergeGold.mergeGold(list, 20));

    }
}
