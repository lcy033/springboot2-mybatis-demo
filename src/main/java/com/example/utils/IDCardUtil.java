package com.example.utils;

import java.util.Date;


/**
 * 通过身份证提取信息
 */
public class IDCardUtil {
    private IDCardUtil() {
    }

    /**
     * 身份证获取性别
     */
    public static String getGender(String idCard) {
        int length = idCard.length();
        int temp = length == 18 ? Integer.parseInt(idCard.substring(length - 2, length - 1), 10)
                : Integer.parseInt(idCard.substring(length - 1), 10);

        return temp % 2 == 0 ? "FEMALE" : "MALE";
    }

    /**
     * 身份证获取性别
     */
    public static String getZHGender(String idCard) {
        int length = idCard.length();
        int temp = length == 18 ? Integer.parseInt(idCard.substring(length - 2, length - 1), 10)
                : Integer.parseInt(idCard.substring(length - 1), 10);

        return temp % 2 == 0 ? "女" : "男";
    }

    /**
     * 身份证获取生日
     */
    @Deprecated
    public static Date getBirthday(String idCard) {
        int length = idCard.length();
        String birthday = length == 18 ? idCard.substring(6, 10) + idCard.substring(10, 12) + idCard.substring(12, 14)
                : "19" + idCard.substring(6, 8) + idCard.substring(8, 10) + idCard.substring(10, 12);
        return   DateUtil.stringFormatDate2(birthday,"yyyyMMdd");
    }

}
