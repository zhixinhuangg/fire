package com.dmsoft.fire.config;

import com.dmsoft.fire.annotation.ServiceType;
import com.dmsoft.fire.service.impl.AbstractMessageService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhixin.huang
 * @date 2019/6/25 11:53
 */
@Component
public class ServiceTypeProcessor implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext arg) throws BeansException {
        if (applicationContext == null) {
            applicationContext = arg;
        }
        Map<String, Object> serviceTypeMap = applicationContext.getBeansWithAnnotation(ServiceType.class);
        serviceTypeMap.forEach((k, v) -> {
            Class<AbstractMessageService> clazz = (Class<AbstractMessageService>) v.getClass();
            String value = clazz.getAnnotation(ServiceType.class).value();
            ServiceContext.HANDLER_MAP.put(value, clazz);
        });
    }

}
