package com.dmsoft.fire.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhixin.huang
 * @date 2019/6/25 10:27
 */
@Component
public class TransMsgAdapterInitializer extends ChannelInitializer<SocketChannel> {

    @Resource
    private StringDecoder stringDecoder;

    @Resource
    private StringEncoder stringEncoder;

    @Resource
    private ServerHandler serverHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(stringDecoder);
        ch.pipeline().addLast(stringEncoder);
        ch.pipeline().addLast(serverHandler);
    }
}
