package com.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三个数的和（求和）
 * Created by finup on 2020/4/13.
 */
public class Solution {

    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> listAll = new ArrayList<>();
        if (nums.length == 0) {
            return listAll;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int tarage = -nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k){
                if (nums[j] + nums[k] == tarage){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    listAll.add(list);
                    j++;
                    k--;
                    while (j < nums.length && nums[j] == nums[j - 1]){
                        j++;
                    }
                    while (k > j && nums[k] == nums[k + 1]){
                        k--;
                    }
                }else if (nums[j] + nums[k] > tarage){
                    k--;
                }else {
                    j++;
                }
            }
        }
        return listAll;
    }

    public static void main(String[] args) {
        System.out.println(Solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
