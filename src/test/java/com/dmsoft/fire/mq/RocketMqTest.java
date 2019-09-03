package com.dmsoft.fire.mq;

import com.dmsoft.fire.FireApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhixin.huang
 * @date 2019/4/10 15:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FireApplication.class})
public class RocketMqTest {
    @Resource
    private MqProducer mqProducer;

    @Test
    public void testProducer() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mqProducer.sendMessage("Hello RocketMQ " + i, "TopicTest",
                    "TagTest", "Key" + i);
        }
    }


}
