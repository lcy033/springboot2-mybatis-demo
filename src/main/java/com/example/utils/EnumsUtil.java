package com.example.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 枚举处理
 */
public class EnumsUtil {

    /**
     * 获取枚举list 加工
     * @param value 枚举数组
     * @return list
     */
    public static List<Map<String, String>> getEnumList(Enum[] value) {
        List<Map<String, String>> list = new ArrayList<>();
        for (Enum status : value) {
            Map<String, String> map = new HashMap<>();
            map.put("key", status.name());
            map.put("value", status.toString());
            list.add(map);
        }
        return list;
    }
    public static List<String> getEnumLists(Enum[] value) {
        List<String> list = new ArrayList<>();
        for (Enum status : value) {
            list.add(status.name());
        }
        return list;
    }
    /**
     * 获取枚举key value 加工
     * @param value 枚举
     * @return map
     */
    public static Map<String, String> getEnum(Enum value) {
        Map<String, String> map = new HashMap<>();
        map.put("key", value.name());
        map.put("value", value.toString());
        return map;
    }

    /**
     * 判断枚举值target在sorces是否存在
     * @param target
     * @param sources
     * @return
     */
    public static boolean inEnums(Enum target, Enum... sources) {
        for (Enum source : sources) {
            if (target == source) {
                return true;
            }
        }
        return false;
    }
    
}