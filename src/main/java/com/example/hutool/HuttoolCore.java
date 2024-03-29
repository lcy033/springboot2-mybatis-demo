package com.example.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;

import java.util.HashMap;

public class HuttoolCore {

    public static void main(String[] args) {
//        int a = 1;
//        //aStr为"1"
//        String aStr = Convert.toStr(a);
//        System.out.println(aStr);
//
//        long[] b = {1, 2, 3, 4, 5};
//        //bStr为："[1, 2, 3, 4, 5]"
//        String bStr = Convert.toStr(b);
//        System.out.println(bStr);

        String[] b = { "1", "2", "3", "4" };
//结果为Integer数组
        Integer[] intArray = Convert.toIntArray(b);
        System.out.println(intArray);
        long[] c = {1,2,3,4,5};
//结果为Integer数组
        Integer[] intArray2 = Convert.toIntArray(c);
        System.out.println(intArray2);
        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = IdUtil.randomUUID();
//生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = IdUtil.simpleUUID();
    }

    /**
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int a = nums[i];
            for (int j = i + 1; j < length; j++) {
                int b = nums[j];
                if (a + b == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }

            }
        }
        return result;
    }

    public static int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        int length = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                result[0] = map.get(complement);
                result[1] = i;
                return result;
            }

            map.put(nums[i], i);
        }

        return result;
    }

}
