package com.example.test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author : LCY
 * @date : create in 2023/7/14 11:17 AM
 */
public class LeetCode {

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

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15, 3, 6, 8, 9, 10, 12, 13, 14, 16, 17, 18 };
        int target = 35;
        System.out.println(System.currentTimeMillis());
        int[] result = twoSum1(nums, target);
        System.out.println(System.currentTimeMillis());
        System.out.println(Arrays.toString(result));
    }
}
