package com.javarush.task.task34.task3408;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        V value = cache.get(key);
        if (value == null) {
            value = clazz.getConstructor(key.getClass()).newInstance(key);
            cache.put(key, value);
        }
        return value;
    }

    public boolean put(V obj) {
        //TODO add your code here
        Method methodGet;
        K key;
        try {
            methodGet = obj.getClass().getDeclaredMethod("getKey", null);
            methodGet.setAccessible(true);
            key = (K) methodGet.invoke(obj,null);
            cache.put(key, obj);
            return true;
        } catch (Exception ignored) {

        }

        return false;
    }

    public int size() {
        return cache.size();
    }
}
