package cn.doitedu.kafka.kafkaexec;

/**
 * ClassName: Test01
 * Package: cn.doitedu.kafka.exec
 * Description:
 *
 * @Author JWT
 * @Create 2025/7/12 10:48
 * @Version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

/**
 * 创建一个topic
 * [root@doit01 ~]# kafka-topics.sh --create --topic doit30-events --partitions 3 --replication-factor 2 --zookeeper doit01:2181
 * <p>
 * 可以用命令去监视这个topic是否有数据到达：
 * [root@doit01 ~]# kafka-console-consumer.sh --topic doit30-events --bootstrap-server doit01:9092
 * <p>
 * <p>
 * 需求：
 * 写一个生产者，不断去生成 “用户行为事件”数据 并写入kafka
 * {"guid":1,"eventId":"pageview","timeStamp":1637868346789}
 * {"guid":1,"eventId":"addcart","timeStamp":1637868346966}
 * {"guid":2,"eventId":"applaunch","timeStamp":1637868346967}
 * .....
 * <p>
 * <p>
 * 需求2： 写一个消费者，不断地从kafka中取消费如上“用户行为事件”数据，并做如下加工处理：
 * 给每一条数据，添加一个字段，来标识，该条数据所属的用户的id在今天是否是第一次出现，如是，则标注1 ；否则，标注0
 * {"guid":1,"eventId":"pageview","timeStamp":1637868346789,"flag":1}
 * {"guid":1,"eventId":"addcart","timeStamp":1637868346966,"flag":0}
 * {"guid":2,"eventId":"applaunch","timeStamp":1637868346967,"flag":1}
 */
public class Consumer02 {
    public static void main(String[] args) throws IOException {
//        kafka-topics.sh --create --topic doit30-events --partitions 3 --replication-factor 2 --bootstrap-server hadoop202:9092
        Properties prop = new Properties();
        prop.load(Consumer02.class.getClassLoader().getSystemResourceAsStream("consumer.properties"));

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(prop);

        kafkaConsumer.subscribe(Arrays.asList("doit30-events2"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {


            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

            }
        });
//              定时过滤器

        Timer timer = new Timer();

        boolean flag = true;
        HashMap<Integer, JSONObject> map = new HashMap<>();  //我这里是用map去实现，实际上可以用布隆过滤器
        long l = System.currentTimeMillis();
        timer.scheduleAtFixedRate(new StatisticTask(map),5000,10000);
        while (flag) {
    // *   需求1： 写一个消费者，不断地从kafka中取消费如上“用户行为事件”数据，并做统计计算：
    // *       每 5分钟，输出一次截止到当时的数据中出现过的用户总数
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(10));
                for (ConsumerRecord<String, String> record : records) {
                    ConsumerRecord<String, String> record1 = record;
                    JSONObject jsonObject = JSONObject.parseObject(record.value());
                    Integer guid = jsonObject.getInteger("guid");
                   if ( !map.containsKey(guid)){
                       jsonObject.put("flag", 0);
                   }else {
                       jsonObject.put("flag", 1);
                   }
                    map.put(guid, jsonObject);  // 安全修改 ConcurrentHashMap
                }
            }
            kafkaConsumer.close();

        }

}

class StatisticTask extends TimerTask {

    public Map map;


    public StatisticTask( Map map) {
        this.map=map;



    }

    @Override
    public void run() {
        System.out.println(JSON.toJSONString(map));
    }
}
