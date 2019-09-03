package com.dmsoft.fire.config;

import com.dmsoft.fire.netty.TransMsgAdapterInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author zhixin.huang
 * @date 2019/6/25 10:36
 */
@Configuration
public class NettyServerConfig {

    @Resource
    private TransMsgAdapterInitializer transMsgAdapterInitializer;

    @Value("${netty.boss.count}")
    private int bossCount;

    @Value("${netty.work.count}")
    private int workCount;

    @Value("${socket.keepAlive}")
    private boolean keepAlive;

    @Value("${socket.backlog}")
    private int backlog;

    @Bean
    public ServerBootstrap serverBootstrap() {

        //创建辅助工具类ServerBootstrap，用于服务器通道的一系列配置
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup(), workGroup()).//绑定两个线程组
                channel(NioServerSocketChannel.class).//指定NIO模式。NioServerSocketChannel对应TCP，NioDatagramChannel对应UDP
                option(ChannelOption.SO_BACKLOG, backlog).//指定缓冲区大小
                option(ChannelOption.SO_KEEPALIVE, keepAlive).//是否保持长连接
                handler(new LoggingHandler()).
                childHandler(transMsgAdapterInitializer);
        return serverBootstrap;
    }

    @Bean
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossCount);
    }

    @Bean
    public NioEventLoopGroup workGroup() {
        return new NioEventLoopGroup(workCount);
    }

    @Bean
    public StringEncoder stringEncoder() {
        return new StringEncoder();
    }

    @Bean
    public StringDecoder stringDecoder() {
        return new StringDecoder();
    }
}