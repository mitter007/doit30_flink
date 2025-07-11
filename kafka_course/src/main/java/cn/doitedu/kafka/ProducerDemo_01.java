package cn.doitedu.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * ClassName: ProducerDemo_01
 * Package: cn.doitedu.kafka
 * Description:
 *
 * @Author JWT
 * @Create 2025/7/11 15:17
 * @Version 1.0
 */
public class ProducerDemo_01 {
    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop202:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 100; i++) {
            if (i%2==1){
                ProducerRecord<String, String> msg = new ProducerRecord<>("test1","奇数", i+"");
                producer.send(msg);
            }else {
                ProducerRecord<String, String> msg = new ProducerRecord<>("test2", "偶数",i+"");
                producer.send(msg);
            }
//      异步发送的
            Thread.sleep(100);

        }
        producer.close();


    }
}
