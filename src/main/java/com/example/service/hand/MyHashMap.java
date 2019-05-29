package com.example.service.hand;

/**
 * Created by finup on 2019/4/28.
 */
public class MyHashMap<K, V> implements MyMap<K, V> {

    //数组默认长度初始化
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    //阀值比例
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int defaultInitialSize;

    private float defaultLoadFactor;

    //MAP 当中 entry 的数量

    private int entryUseSize;

    private Entry<K, V>[] table = null;

    public MyHashMap(){
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int defaultInitialCapacity, float defaultLoadFactor){
        if (defaultInitialCapacity < 0){
            throw new IllegalArgumentException("illegal initial capacity :" + defaultInitialCapacity);
        }

        if (defaultInitialCapacity <= 0 || Float.isNaN(defaultLoadFactor)){
            throw new IllegalArgumentException("illegal load factor :" + defaultLoadFactor);
        }

        this.defaultInitialSize = defaultInitialCapacity;
        this.defaultLoadFactor = defaultLoadFactor;

        table = new Entry[this.defaultInitialSize];
    }

    @Override
    public V put(K k, V v) {
        V oldValue = null;
        //是否需要扩容？
        //扩容完毕 肯定需要重新散列
        return null;
    }

    @Override
    public Object get(Object o) {
        return null;
    }
}
