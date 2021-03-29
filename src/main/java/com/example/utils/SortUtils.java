package com.example.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 排序
 */
public class SortUtils {

    /**
     * 冒泡 时间O(n2) 空间O(1)
     */
    private static void sort1(int[] arr) {
        int s = 0;
        for (int i = 0; i < arr.length-1; i++){
            for (int j = i+1; j < arr.length; j++){
                //和后一个数相比，比它大就交换
                if (arr[i] > arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
                s += 1;
            }
        }
        System.out.println("循环"+ s);
    }

    /**
     * 冒泡V2 时间O(n2) 空间O(1)
     */
    private static void sort1V2(int[] arr) {
        int s = 0;
        for (int i = 0; i < arr.length; i++){
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < arr.length - i - 1; j++){
                //和后一个数相比，比它大就交换
                if (arr[j] > arr[j + 1]){
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
                s += 1;
            }
            if (isSorted){
                break;
            }
        }
        System.out.println("循环"+ s);
    }

    private static void sort(int array[]) {
        int tmp = 0;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * 插入排序，a 表示数组
     * 分为两个区域，有序、无序
     */
    private static void insertionSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        for (int i = 1; i < a.length; i++) {
            // 需要交换的元素
            int value = a[i];
            // 第一个元素认为有序
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    // 数据移动
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            // 插入数据
            a[j + 1] = value;
        }
    }


    /**
     * 选择 时间O(n2) 空间O(1)
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
        if (low < high) {
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

    /**
     *
     * @param arr 要二分搜索的数组
     * @param key 要查找的关键字
     * @param start 起始索引
     * @param end 结尾索引
     * @return 若搜索到这个元素，则返回数组的索引下标；否则返回-1
     */
    private static int binary(int[] arr, int key, int start, int end){
        while (start <= end){
            int mid = (start + end) / 2;
            if (arr[mid] > key) {
                end = mid - 1;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     *
     * @param arr 要二分搜索的数组
     * @param key 要查找的关键字
     * @param start 起始索引
     * @param end 结尾索引
     * @return 若搜索到这个元素，则返回数组的索引下标；否则返回-1
     */
    private static int binary1(int[] arr, int key, int start, int end){
        if (start <= end){
            int mid = (start + end) / 2;
            if (arr[mid] > key) {
                return binary1(arr, key, start, mid - 1);
            } else if (arr[mid] < key) {
                return binary1(arr, key, mid + 1, end);
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 提取数字
     * 如果字符串中出现连续的 数字 则认为 字符相等
     * 123abc12b1234  12abc1b123 出现连续字符串 123
     * 123abc12b1234  2abc4b23 最后的输出结果
     */
    private static String getNumber(String n1, String n2){
        String[] list1 = n1.split(",");
        String[] list2 = n2.split(",");
        for (int i = 0; i < list1.length; i++) {
            if (list1[i] == null){
                continue;
            }
            String max = null;
            for (int j = 0; j < list2.length; j++) {
                if (list2[i] == null){
                    continue;
                }

            }
        }
        return null;
    }

    private static String getN(String str) {
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll(",").trim();
    }

    public static void main(String[] args) {
//        String s1 = "123abc12b1234";
//        String s2 = "2abc4b23";
//        String a1 = getN(s1);
//        String a2 = getN(s2);
//        String n1 = a1.length() > a2.length() ? a2 : a1;
//        String n2 = a1.length() > a2.length() ? a1 : a2;
//        //n1短 n2长
//        System.out.println(getNumber(n1, n2));

//        int[] arr1 = {1,6,22,2,4,9};
//        sort1(arr1);
//        System.out.println(Arrays.toString(arr1));
//        int[] arr1V2 = {1,6,22,2,4,9};
//        sort1V2(arr1V2);
//        System.out.println(Arrays.toString(arr1V2));
//        int[] sort = {1,6,22,2,4,9};
//        sort(sort);
//        System.out.println(Arrays.toString(sort));
//        int[] arr2 = {11,16,22,2,14,9};
//        sort2(arr2);
//        System.out.println(Arrays.toString(arr2));
//        int[] arr3 = {21,26,22,12,4,29};
//        sort3(arr3, 3, 5);
//        System.out.println(Arrays.toString(arr3));
//
//        int[] arr = { 11, 22, 33, 44, 55, 66, 77 };
//        int index1 = binary(arr, 11, 0, arr.length - 1);
//        int index2 = binary(arr, 66, 0, arr.length - 1);
//        System.out.println(index1);
//        System.out.println(index2);
        int[] arr = {3,2,1};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
//        sort1(arr);
//        sort1V2(arr);

    }
}