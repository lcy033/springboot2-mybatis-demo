package com.example.test;

import com.example.test.chushihua.Son;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;

public class Topic {


    private static int getMin(int[] arr) {

        if (arr == null || arr.length <= 0) {
            return -1;
        }
        int min = arr[0];
        for (int value : arr) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    private static void reversal(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        int j = arr.length - 1;
        for (int i = 0; i < j; i++) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            j--;
        }
    }

    private static void sort(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    private static void sort1(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    private static int binary(int[] arr, int key) {
        if (arr == null || arr.length <= 0) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
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

    private static int binary(int[] arr, int key, int start, int end) {
        if (arr == null || arr.length <= 0) {
            return -1;
        }
        if (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] > key) {
                return binary(arr, key, start, mid - 1);
            } else if (arr[mid] < key) {
                return binary(arr, key, mid + 1, end);
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static void az() {
        char[] arr = new char[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = (char) ('A' + i);
        }
        for (char c : arr) {
            System.out.println(c);
        }
    }

    private static int[] addEnd(int[] arr, int value) {
        if (arr == null || arr.length <= 0) {
            return arr;
        }
        int[] arr1 = new int[arr.length + 1];
//        System.arraycopy(arr, 0, arr1, 0, arr1.length - 1);
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            arr1[j] = arr[i];
            j++;
        }
        arr1[arr1.length - 1] = value;
        return arr1;
    }

    private static int[] add(int[] arr, int index, int value) {
        if (arr == null
                || arr.length <= 0
                || index > arr.length + 1) {
            return arr;
        }
        int[] arr1 = new int[arr.length + 1];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                arr1[j] = value;
                j += 1;
            }
            arr1[j] = arr[i];
            j++;
        }
        if (arr1.length == index) {
            arr1[arr1.length - 1] = value;
        }
        return arr1;
    }

    private static boolean compare(int[] arr, int[] arr1) {
        if (arr == null ^ arr1 == null) {
            return false;
        }
        if (arr == arr1) {
            return true;
        }
        if (arr.length == 0 ^ arr1.length == 0) {
            return false;
        }
        if (arr.length == 0) {
            return true;
        }
        if (arr.length != arr1.length) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr1[i]) {
                return false;
            }
        }
        return true;
    }

    private static void hundredMoneyHundredChicken() {
        for (int cock = 0; cock <= 20; cock++) {
            for (int hen = 0; hen <= 33; hen++) {
                if ((100 - cock - hen) % 3 == 0 && cock * 5 + hen * 3 + (100 - cock - hen) / 3 == 100) {
                    System.out.println("公鸡" + cock + "只，母鸡" + hen + "只，小鸡" + (100 - cock - hen) + "只。");
                }
            }
        }
    }

    private static int getRabbitNum(int month) {
        if (month < 0) {
            return -1;
        }
        if (month < 3) {
            return 2;
        }
        int[] arr = new int[month];
        arr[0] = 2;
        arr[1] = 2;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[month - 1];
    }

    public static <T> T test(T t) {
        return t;
    }

    public static void main(String[] args) {
//        int[] arr = {2, 3, 4, 1, -6, 5};
//        int min = Topic.getMin(arr);
//        System.out.println(min);

//        Topic.reversal(arr);
//        System.out.println(Arrays.toString(arr));

//        Topic.sort(arr);
//        System.out.println(Arrays.toString(arr));

//        int index2 = binary(arr, 66);
//        System.out.println(index2);

//        Topic.az();

//        int[] arr1 = Topic.addEnd(arr, 9);
//        System.out.println(Arrays.toString(arr1));

//        int[] arr1 = Topic.add(arr, 7, 12);
//        System.out.println(Arrays.toString(arr1));

//        int[] arr = null;
//        System.out.println(arr[0]);
//        int[] arr = new int[]{};
//        System.out.println(arr[3]);

//        boolean is = Topic.compare(null, null);
//        System.out.println(is);

//        System.out.println(Topic.getRabbitNum(20));
//        for (int cock = 0; cock <= 20; cock++) {
//            for (int hen = 0; hen <= (100 - (cock * 5)) / 3 ; hen++) {
//                if ((100 - cock - hen) % 3 == 0 && cock * 5 + hen * 3 + (100 - cock - hen) / 3 == 100) {
//                    System.out.println("公鸡" + cock + "只，母鸡" + hen + "只，小鸡" + (100 - cock - hen) + "只。");
//                }
//            }
//        }

    }


}
