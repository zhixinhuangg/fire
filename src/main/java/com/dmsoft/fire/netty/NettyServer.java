package com.dmsoft.fire.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author zhixin.huang
 * @date 2019/4/22 15:23
 */
@Slf4j
@Component
public class NettyServer {

    @Value("${tcp.port}")
    private int tcpPort;

    @Resource
    private ServerBootstrap serverBootstrap;

    @Resource
    private NioEventLoopGroup bossGroup;

    @Resource
    private NioEventLoopGroup workGroup;

    private ChannelFuture serverChannelFuture;

    @PostConstruct
    public void start() throws Exception {
        log.info("****************** starting socket server at [" + tcpPort + "]******************");
        //绑定端口，bind返回future（异步），加上sync阻塞在获取连接处
        serverChannelFuture = serverBootstrap.bind(tcpPort).sync();
    }

    @PreDestroy
    public void stop() throws Exception {
        log.info("****************** socket server is close ******************");
        serverChannelFuture.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
}
