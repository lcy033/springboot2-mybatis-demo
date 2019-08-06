package com.example.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 排序
 */
public class SortUtils {

    /**
     * 冒泡
     */
    private static void sort1(int[] arr) {
        for (int i = 0; i < arr.length-1; i++){
            for (int j = i+1; j < arr.length; j++){
                //和后一个数相比，比它大就交换
                if (arr[i] > arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    /**
     * 选择
     */
    private static void sort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            //求最小值的位置
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            //和最小值交换位置
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }

    /**
     * 快速
     */
    private static void sort3(int[] arr, int low, int high) {
        int mediaVal, mediaIndex;// pivot->位索引;p_pos->轴值。
        if(low<high) {
            mediaIndex = low;
            mediaVal = arr[mediaIndex];
            for (int i = low + 1; i <= high; i++) {
                //将小于中间数的放到前面，大于的放到后面
                if (arr[i] < mediaVal) {
                    mediaIndex++;
                    int tmp = arr[mediaIndex];
                    arr[mediaIndex] = arr[i];
                    arr[i] = tmp;
                }
            }
            //将中间数放到中间
            int tmp = arr[low];
            arr[low] = arr[mediaIndex];
            arr[mediaIndex] = tmp;

            sort3(arr, low, mediaIndex - 1);// 排序左半部分
            sort3(arr, mediaIndex + 1, high);// 排序右半部分
        }
        }

    public static void main(String[] args) {
        int[] arr1 = {1,6,22,2,4,9};
        sort1(arr1);
        System.out.println(Arrays.toString(arr1));
        int[] arr2 = {11,16,22,2,14,9};
        sort2(arr2);
        System.out.println(Arrays.toString(arr2));
        int[] arr3 = {21,26,22,12,4,29};
        sort3(arr3, 3, 5);
        System.out.println(Arrays.toString(arr3));
    }
}