package cn.doitedu.kafka.kafkaexec;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.StandardCharsets;

public class BloomFilterDemo {
    public static void main(String[] args) {
        // 创建布隆过滤器：预计插入 1000 条数据，误判率 0.01（1%）
        BloomFilter<String> bloomFilter = BloomFilter.create(
                Funnels.stringFunnel(StandardCharsets.UTF_8),
                1000,
                0.01);

        // 添加元素
        bloomFilter.put("alice");
        bloomFilter.put("bob");

        // 判断是否存在
        System.out.println(bloomFilter.mightContain("alice")); // true
        System.out.println(bloomFilter.mightContain("bob"));   // true
        System.out.println(bloomFilter.mightContain("charlie")); // 可能是 false（但可能为 true，取决于误判率）
    }
}
