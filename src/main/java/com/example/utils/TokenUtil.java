package com.example.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * token加解密工具类
 */
public class TokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenUtil.class);
    //对称密码
    private static final String AES_PASSWORD_TOKEN = "1243453457976443";//NOSONAR


    private TokenUtil() {

    }

    /**
     * 生成加密token
     * 规则：uid
     * dongChen
     *
     * @param uid
     * @return
     */
    public static String generateToken(String uid) {
        return encrypt(uid);
    }

    /**
     * 通过加密token获取UID
     *
     * @param encryptToken
     * @return
     */
    public static String getUID(String encryptToken) {
        if (StringUtils.isEmpty(encryptToken)) {
            return null;
        }
        return decrypt(encryptToken);
    }


    /**
     * token加密操作,使用aes对称加密32位算法,注意对称密码必须是16的倍数
     */
    private static String encrypt(String str) {
        try {
            byte[] kb = AES_PASSWORD_TOKEN.getBytes("utf-8");
            SecretKeySpec sks = new SecretKeySpec(kb, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//算法/模式/补码方式
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            byte[] eb = cipher.doFinal(str.getBytes("utf-8"));
            return Base64.getEncoder().encodeToString(eb);
        } catch (Exception e) {
            LOGGER.error("uid{},token加密失败，失败原因{}", str, e);
            return null;
        }
    }

    /**
     * 解密操作
     *
     * @param str
     * @return
     */
    private static String decrypt(String str) {
        try {
            byte[] kb = AES_PASSWORD_TOKEN.getBytes("utf-8");
            SecretKeySpec sks = new SecretKeySpec(kb, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, sks);
            byte[] by = Base64.getDecoder().decode(str);
            byte[] db = cipher.doFinal(by);
            return new String(db);
        } catch (Exception e) {
            LOGGER.error("uid{},token解密失败，失败原因{}", str, e);
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(generateToken("1580995"));
        System.out.println(TokenUtil.getUID("gH9MPR1koEvqu5T2bWnMMw=="));
    }

}
