package com.example.utils;

import java.util.regex.Pattern;

/**
 * 校验器：利用正则表达式校验邮箱、手机号等
 */
public class PatterUtils {

    /**
     * 正则表达式：验证用户名
     */
    private static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 正则表达式：校验正整数(进件号)
     */
    private static final String REGEX_POSITIVE_INTEGER  = "^[1-9]\\d*$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^[1][3-9][0-9]{9}$";

    /**
     * 正则表达式：验证邮箱
     */
    private static final String REGEX_EMAIL = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

    /**
     * 正则表达式：验证汉字
     */
    private static final String REGEX_CHINESE = "([\\u4e00-\\u9fa5]{2,4})";

    /**
     * 正则表达式：验证身份证
     */
    private static final String REGEX_ID_CARD = "(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$)";

    /**
     * 正则表达式：验证URL
     */
    private static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证金额
     */
    private static final String REGEX_AMOUNT = "^[1-9]\\d*(\\.\\d+)?$";
    /**
     * 正则表达式：验证单位电话（座机号）
     */
    private static final String REGEX_TELPHONE = "^(0\\d{2,3}-\\d{7,8})(-\\d{1,4})?$";
    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    @Deprecated
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验正整数
     *
     * @param number
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPositiveInteger(String number) {
        return Pattern.matches(REGEX_POSITIVE_INTEGER, number);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     */
    public static boolean isAmount(String amount) {
        return Pattern.matches(REGEX_AMOUNT, amount);
    }

    /**
     * 校验座机号码是否匹配
     */
    public static boolean isTelPhone(String telphone) {
        return Pattern.matches(REGEX_TELPHONE, telphone);
    }

}
