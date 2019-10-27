package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud;

import com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.stream.RabbitmqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Test
    public void contextLoads() {

    }

    @Autowired
    private RabbitmqSender rabbitSend;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Test
    public void testSend1() {
        Map<String, Object> prperties = new HashMap<>();
        prperties.put("number", "12345");
        prperties.put("send_time", simpleDateFormat.format(new Date()));
        try {
            rabbitSend.sendMessage("Hello RabbitMQ For SpringCloud", prperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 睡2秒ack即为true
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
