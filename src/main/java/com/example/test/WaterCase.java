package com.example.test;

/**
 * 最大存水量问题
 * Created by finup on 2020/3/19.
 */
public class WaterCase {

    /**
     * 获取左侧最高
     * @param index 第几根柱子
     * @param waters 柱子的高度
     * @return
     */
    private int getLiftMax(int index, int[] waters){
        int max = waters[index];
        for (int i = 0; i < index; i++) {
            if (waters[i] > max){
                max = waters[i];
            }
        }
        return max;
    }

    /**
     * 获取右侧最高
     * @param index 第几根柱子
     * @param waters 柱子的高度
     * @return
     */
    private int getRightMax(int index, int[] waters) {
        int max = waters[index];
        for (int i = index; i < waters.length; i++) {
            if (waters[i] > max){
                max = waters[i];
            }
        }
        return max;
    }

    /**
     * 获取存水量
     * @param index 第几根柱子
     * @return
     */
    private String getWater(int index, int[] nums){
        if (index <= 0){
            return "柱子数量异常";
        }
        if (index > nums.length){
            return "超出柱子数量";
        }
        int num = nums[index];
        //获取左侧跟右侧最高的柱子取最低的一根然后减去当前柱子的高度
        int water = Math.min(getLiftMax(index, nums), getRightMax(index, nums)) - num;
        return String.format("第%s根柱子存水量是%s", index, water);

    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 2, 4};
        WaterCase waterCase = new WaterCase();
        String result = waterCase.getWater(1, nums);
        System.out.println(result);

    }
}
