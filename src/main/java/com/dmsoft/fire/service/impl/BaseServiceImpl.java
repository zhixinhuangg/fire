package com.dmsoft.fire.service.impl;

import com.dmsoft.fire.config.ServiceContext;
import com.dmsoft.fire.service.BaseService;
import io.netty.channel.socket.SocketChannel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhixin.huang
 * @date 2019/6/25 11:37
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Resource
    private ServiceContext serviceContext;

    @Override
    public void handle(Object msg, SocketChannel ch) {
        System.out.println("开始处理消息");
        AbstractMessageService aaa = serviceContext.getInstance("order");
        aaa.handle(msg, ch);
    }
}
