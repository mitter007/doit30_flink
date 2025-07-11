package cn.doitedu.kafka.test;

import org.apache.kafka.common.serialization.Serializer;

/**
 * ClassName: Serializer01
 * Package: cn.doitedu.kafka.test
 * Description:
 *
 * @Author JWT
 * @Create 2025/7/11 15:25
 * @Version 1.0
 */
public class Serializer01 implements Serializer {


    @Override
    public byte[] serialize(String topic, Object data) {
        return new byte[0];
    }
}
