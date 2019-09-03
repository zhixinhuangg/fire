package com.dmsoft.fire.service.impl;

import io.netty.channel.socket.SocketChannel;

/**
 * @author zhixin.huang
 * @date 2019/6/25 11:51
 */
public abstract class AbstractMessageService {

    public abstract void handle(Object msg, SocketChannel ch);
}
