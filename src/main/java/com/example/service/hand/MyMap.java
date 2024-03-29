package com.example.service.hand;

/**
 * Created by finup on 2019/4/28.
 */
public interface MyMap<K, V> {

    public V put(K k, V v);

    public V get(K k);

    interface Entry<K, V>{
        public K getKey();
        public V getValue();
    }
}
