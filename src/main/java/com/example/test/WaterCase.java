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
        System.out.println("左侧：" + max);
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
        System.out.println("右侧：" + max);
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
        int num = nums[index-1];
        //如果是第一根或者最后一根
        if (index == 1 || index == nums.length){
            return String.format("第%s根柱子存水量是%s", index, 0);
        }
        //获取左侧跟右侧最高的柱子取最低的一根然后减去当前柱子的高度
        int water = Math.min(getLiftMax(index, nums), getRightMax(index, nums)) - num;
        System.out.println("当前：" + num);
        return String.format("第%s根柱子存水量是%s", index, Math.max(water, 0));

    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 5, 2, 4, 2, 4, 5};
        WaterCase waterCase = new WaterCase();
        String result = waterCase.getWater(8, nums);
        System.out.println(result);

    }
}
