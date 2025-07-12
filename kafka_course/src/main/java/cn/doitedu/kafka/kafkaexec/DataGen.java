package cn.doitedu.kafka.kafkaexec;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * ClassName: MyDataGen
 * Package: cn.doitedu.kafka.exec
 * Description:
 *
 * @Author JWT
 * @Create 2025/7/12 10:55
 * @Version 1.0
 */
public class DataGen {

    static User  getUser(){
        User user = new User();
       user.setGuid(RandomUtils.nextInt(1,100));
       user.setEventId(RandomStringUtils.randomAlphabetic(5,8));
       user.setTimestamp(System.currentTimeMillis());
       return user;
    }

    public static void main(String[] args) {
        String string = DataGen.getUser().toString();
        System.out.println(string);
    }
}
