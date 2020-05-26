package com.example.enums;

public enum RoleType {

    MASTER("超级权限"),

    ROOT("大哥"),

    TEST("测试");

    private final String type;

    RoleType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}
