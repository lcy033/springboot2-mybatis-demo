package com.example.test;

/**
 * Created by finup on 2019/4/4.
 */
public class AbTest {
    public static void main(String[] args) {
        MyObject object1 = new MyObject();
        MyObject object2 = new MyObject();

        object1.object = object2;
        object2.object = object1;

        object1 = null;
        object2 = null;
    }
}

class MyObject{
    MyObject object;
}
