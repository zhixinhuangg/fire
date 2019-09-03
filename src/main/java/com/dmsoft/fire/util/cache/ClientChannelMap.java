package com.dmsoft.fire.util.cache;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhixin.huang
 * @date 2019/6/25 10:12
 */
public class ClientChannelMap {
    private static Map<String, SocketChannel> map = new ConcurrentHashMap<String, SocketChannel>();

    public static void add(String clientId, SocketChannel socketChannel) {
        if (!StringUtils.isNotEmpty(clientId)) {
            return;
        }
        map.put(clientId, socketChannel);
    }

    public static Channel get(String clientId) {
        if (!StringUtils.isNotEmpty(clientId)) {
            return null;
        }
        return map.get(clientId);
    }

    public static void remove(SocketChannel socketChannel) {
        for (Map.Entry<String, SocketChannel> entry : map.entrySet()) {
            if (entry.getValue() == socketChannel) {
                map.remove(entry.getKey());
            }
        }
    }

    public static void remove(String clientId) {
        if (!StringUtils.isNotEmpty(clientId)) {
            return;
        }
        map.remove(clientId);
    }
}
