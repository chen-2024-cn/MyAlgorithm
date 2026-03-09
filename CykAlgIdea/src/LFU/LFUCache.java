package LFU;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * LFU工作原理
 * 每次访问都将数据的频率加1
 * 当缓存满时，淘汰频率最低且最久未使用的项
 * 实现方式：使用一个HashMap存储缓存项的键值对，一个HashMap存储每个键的使用频率，一个HashMap存储每个频率对应的键集合
 */

public class LFUCache<K, V> {
    // 存储缓存项的键值对
    private final Map<K, V> cache;
    // 存储每个键的使用频率
    private final Map<K, Integer> frequencyMap;
    // 存储每个频率对应的键集合，使用LinkedHashSet保持插入顺序
    private final Map<Integer, LinkedHashSet<K>> frequencyKeysMap;
    // 缓存容量
    private final int capacity;
    // 当前最小频率
    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
        this.frequencyKeysMap = new HashMap<>();
        this.minFrequency = 0;
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }

        // 增加键的使用频率
        increaseFrequency(key);
        return cache.get(key);
    }

    public void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }

        // 如果键已存在，更新值并增加频率
        if (cache.containsKey(key)) {
            cache.put(key, value);
            increaseFrequency(key);
            return;
        }

        // 如果缓存已满，移除使用频率最低且最久未使用的项
        if (cache.size() >= capacity) {
            evict();
        }

        // 添加新键值对
        cache.put(key, value);
        frequencyMap.put(key, 1);

        // 将键添加到频率为1的集合中
        frequencyKeysMap.putIfAbsent(1, new LinkedHashSet<>());
        frequencyKeysMap.get(1).add(key);

        // 新插入的项频率最低为1
        minFrequency = 1;
    }

    private void increaseFrequency(K key) {
        int freq = frequencyMap.get(key);
        // 更新频率
        frequencyMap.put(key, freq + 1);

        // 从原频率集合中移除
        frequencyKeysMap.get(freq).remove(key);

        // 如果原频率集合为空且是最小频率，更新最小频率
        if (freq == minFrequency && frequencyKeysMap.get(freq).isEmpty()) {
            minFrequency++;
        }

        // 添加到新频率集合中
        frequencyKeysMap.putIfAbsent(freq + 1, new LinkedHashSet<>());
        frequencyKeysMap.get(freq + 1).add(key);
    }

    private void evict() {
        // 获取最小频率的键集合
        LinkedHashSet<K> keys = frequencyKeysMap.get(minFrequency);
        // 获取第一个插入的键（最久未使用）
        K evictKey = keys.iterator().next();

        // 从各个映射中移除
        keys.remove(evictKey);
        cache.remove(evictKey);
        frequencyMap.remove(evictKey);
    }

    private void printCache() {
        System.out.println("Current cache: " + cache);
        System.out.println("Frequency map: " + frequencyMap);
        System.out.println("Frequency keys map: " + frequencyKeysMap);
        System.out.println("Min frequency: " + minFrequency);
        System.out.println("-------------------------");
    }

    private void clearCache() {
        cache.clear();
        frequencyMap.clear();
        frequencyKeysMap.clear();
        minFrequency = 0;
    }

    // 测试用例
    public static void main(String[] args) {
        LFUCache<Integer, String> cache = new LFUCache<>(2);

        cache.put(1, "One");
        cache.put(2, "Two");
        //(One,Two)
        System.out.println(cache.get(1)); // 返回 "One" (Two, One)

        cache.put(3, "Three"); // 移除键 2(One,Three)
        System.out.println(cache.get(2)); // 返回 null (已被移除)(One,Three)
        System.out.println(cache.get(3)); // 返回 "Three"(One,Three)

        cache.put(4, "Four"); // 移除键 1(Three,Four)
        System.out.println(cache.get(1)); // 返回 null (已被移除)
        System.out.println(cache.get(3)); // 返回 "Three"(Four,Three)
        System.out.println(cache.get(4)); // 返回 "Four"(Three,Four)
        cache.printCache();
        cache.clearCache();
        cache.put(1, "One");
        cache.put(1,"One2");
        cache.printCache();
    }
}