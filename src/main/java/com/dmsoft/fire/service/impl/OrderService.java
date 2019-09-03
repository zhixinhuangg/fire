package com.dmsoft.fire.service.impl;

import com.dmsoft.fire.annotation.ServiceType;
import com.dmsoft.fire.mq.MqProducer;
import io.netty.channel.socket.SocketChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhixin.huang
 * @date 2019/6/25 14:04
 */
@Component
@ServiceType("order")
public class OrderService extends AbstractMessageService {

    @Resource
    private MqProducer mqProducer;

    @Override
    public void handle(Object msg, SocketChannel ch) {
        System.out.println("order");
        mqProducer.sendMessage("order ", "TopicTest",
                "TagTest", "Key");
    }
}
