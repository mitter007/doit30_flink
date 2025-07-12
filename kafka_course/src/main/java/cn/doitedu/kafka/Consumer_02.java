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
public class Consumer_02 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop202:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "do01");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);

//        对称之美和规律之美

        kafkaConsumer.subscribe(Arrays.asList("test2"));
        boolean flag = true;
        while (flag) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(10));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.key());
                record.topic();
                record.partition();
                record.offset();
                record.leaderEpoch();

                System.out.println(record.value());
            }
        }
        kafkaConsumer.close();
    }
}
