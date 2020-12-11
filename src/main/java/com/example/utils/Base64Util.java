package com.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * base64
 */
@Slf4j
public class Base64Util {

    public static String decoder(String text) {
        final Base64.Encoder encoder = Base64.getEncoder();
        final byte[] textByte;
        try {
            textByte = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return encoder.encodeToString(textByte);
    }

    public static String decoder(byte[] textByte) {
        final Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(textByte);
    }

    public static String encoder(String encodedText) {
        final Base64.Decoder decoder = Base64.getDecoder();
        try {
            return new String(decoder.decode(encodedText), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encoder(byte[] encodedTextByte) {
        final Base64.Decoder decoder = Base64.getDecoder();
        String encodedText;
        try {
            encodedText = new String(decoder.decode(encodedTextByte), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return Base64Util.encoder(encodedText);
    }

}
