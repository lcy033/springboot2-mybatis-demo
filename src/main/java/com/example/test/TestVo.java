package com.example.test;

import lombok.ToString;

import java.lang.reflect.Field;

/**
 * @author : LCY
 * @date : create in 2021/1/8 2:25 下午
 */
@ToString
public class TestVo {

    private String test = "test";

    private String text = "text";

    private Integer integer = 0;

    public static void main(String[] args) throws IllegalAccessException {
        TestVo testVo = new TestVo();
        Class c = testVo.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().toString().contains(".String")) {
                field.set(testVo, field.get(testVo).toString() + System.currentTimeMillis());
            }
            if (field.getType().toString().contains(".Integer")) {
                field.set(testVo, 100);
            }
        }
        System.out.println(testVo);
    }

}


