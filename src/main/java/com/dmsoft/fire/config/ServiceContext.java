package com.dmsoft.fire.config;

import com.dmsoft.fire.service.impl.AbstractMessageService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhixin.huang
 * @date 2019/6/25 11:46
 */
@Component
public class ServiceContext {
    public static Map<String, Class<AbstractMessageService>> HANDLER_MAP = Maps.newHashMap();

    public AbstractMessageService getInstance(String type) {
        Class<AbstractMessageService> clazz = HANDLER_MAP.get(type);
        if (clazz == null) {
            throw new IllegalArgumentException("未找类型 : " + type);
        }
        return ServiceTypeProcessor.getBean(clazz);
    }
}
