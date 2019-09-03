package com.dmsoft.fire.openapi.v1;

import com.dmsoft.fire.mq.MqProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhixin.huang
 * @date 2019/6/25 15:56
 */
@RestController
@RequestMapping("api/v1/rocketMq")
public class RocketMqFacade {

    @Resource
    private MqProducer mqProducer;

    @PostMapping("/push")
    public void push() {
        mqProducer.sendMessage("Hello RocketMQ ", "TopicTest",
                "TagTest", "Key");
    }
}
