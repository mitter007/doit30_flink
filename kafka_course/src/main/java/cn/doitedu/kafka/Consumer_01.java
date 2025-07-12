package cn.doitedu.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * ClassName: Consumer_01
 * Package: cn.doitedu.kafka
 * Description:
 *
 * @Author JWT
 * @Create 2025/7/11 15:33
 * @Version 1.0
 */
public class Consumer_01 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop202:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "do01");

//        kafka的消费者，默认是从所属组之前所记录的偏移量开始消费，如果找不到之前记录的偏移量，则从如下参数配置的策略来确定消费起始偏移量
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        kafkaConsumer.subscribe(Arrays.asList("test1"));
        boolean flag = true;
        while (flag) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(10));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.key());

                System.out.println(record.value());
            }
        }
        kafkaConsumer.close();
    }
}
