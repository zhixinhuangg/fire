package com.dmsoft.fire.netty;

import com.dmsoft.fire.service.BaseService;
import com.dmsoft.fire.util.cache.ChannelMap;
import com.dmsoft.fire.util.cache.ClientChannelMap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @author zhixin.huang
 * @date 2019/4/22 15:40
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Resource
    private BaseService baseService;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端 {" + ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress() + "} 已成功连接");
        ChannelMap.add(ctx.channel().id().asLongText(), (SocketChannel) ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelMap.remove(ctx.channel().id().asLongText());
        ClientChannelMap.remove((SocketChannel) ctx.channel());
        log.info("客户端 {" + ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress() + "} 已断开连接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ChannelMap.add(ctx.channel().id().asLongText(), (SocketChannel) ctx.channel());
        System.out.println("收到消息");
        baseService.handle(msg, (SocketChannel) ctx.channel());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
