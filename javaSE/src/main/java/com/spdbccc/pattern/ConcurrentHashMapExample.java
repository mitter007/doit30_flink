package com.spdbccc.pattern;

import java.util.concurrent.*;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        // 创建一个 ConcurrentHashMap 实例
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // 向 map 中添加键值对
        map.put("a", 1);
        map.put("b", 2);

        // 使用 putIfAbsent 方法
        map.putIfAbsent("b", 3);  // "b" 已经有值，不会被覆盖
        map.putIfAbsent("c", 3);  // "c" 不存在，插入 "c" -> 3

        // 获取值
        System.out.println(map.get("a"));  // 输出 1
        System.out.println(map.get("b"));  // 输出 2
        System.out.println(map.get("c"));  // 输出 3

        // 使用 computeIfAbsent 方法
        map.computeIfAbsent("d", key -> 4);  // 如果 "d" 不存在，插入 "d" -> 4

        // 移除元素
        map.remove("b");

        // 输出 map 内容
        System.out.println(map);  // 输出 {a=1, c=3, d=4}
    }
}
