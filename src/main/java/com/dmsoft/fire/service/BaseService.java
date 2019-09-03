package com.dmsoft.fire.service;

import io.netty.channel.socket.SocketChannel;;

/**
 * @author zhixin.huang
 * @date 2019/6/25 11:32
 */
public interface BaseService {

    void handle(Object msg, SocketChannel ch);
}
