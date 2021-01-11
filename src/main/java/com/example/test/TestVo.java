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

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
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
        String s = "hello world";
        Field valueFieldOfString = String.class.getDeclaredField("value");
        // 改变value属性的访问权限
        valueFieldOfString.setAccessible(true);
        // 获取s对象上的value属性的值
        char[] value = (char[]) valueFieldOfString.get(s);
        // 改变value所引用的数组中的第5个字符
        value[0] = 'H';
        value[6] = 'W';
        System.out.println(s);
    }




}


