package com.dmsoft.fire.util.cache;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhixin.huang
 * @date 2019/6/25 10:09
 */
public class ChannelMap {
    private static Map<String, SocketChannel> map = new ConcurrentHashMap<>();

    public static void add(String channelId, SocketChannel socketChannel) {
        if (!StringUtils.isNotEmpty(channelId)) {
            return;
        }
        map.put(channelId, socketChannel);
    }

    public static Channel get(String channelId) {
        if (!StringUtils.isNotEmpty(channelId)) {
            return null;
        }
        return map.get(channelId);
    }

    public static void remove(String channelId) {
        if (!StringUtils.isNotEmpty(channelId)) {
            return;
        }
        map.remove(channelId);
    }
}
