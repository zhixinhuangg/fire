package com.dmsoft.fire.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zhixin.huang
 * @date 2019/4/22 16:09
 */
public class Client {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class).
                    handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture cf = bootstrap.connect("192.168.30.88", 8765).sync();
            cf.channel().writeAndFlush("112233".getBytes());
            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }
}
