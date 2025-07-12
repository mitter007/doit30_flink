package cn.doitedu.kafka.kafkaexec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: User
 * Package: cn.doitedu.kafka.exec
 * Description:
 *
 * @Author JWT
 * @Create 2025/7/12 10:51
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
//     *   写一个生产者，不断去生成 “用户行为事件”数据 并写入kafka
// *   {"guid":1,"eventId":"pageview","timeStamp":1637868346789}
// *   {"guid":1,"eventId":"addcart","timeStamp":1637868346966}
// *   {"guid":2,"eventId":"applaunch","timeStamp":1637868346967}

    Integer guid;
    String eventId;
    Long timestamp;

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());

    }
}
