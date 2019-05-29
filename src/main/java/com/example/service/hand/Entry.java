package com.example.service.hand;

/**
 * Created by finup on 2019/4/28.
 */
public class Entry<K, V> implements MyMap.Entry {

    private K key;

    private V value;

    private Entry<K, V> next;

    public Entry(){

    }

    public Entry(K key, V value, Entry<K, V> next){
        this.key = key;
        this.value = value;
        this.next = next;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }
}
