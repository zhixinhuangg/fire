package com.dmsoft.fire.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhixin.huang
 * @date 2019/4/11 10:26
 */
@Component
public class SnowflakeComponent {
    private static volatile SnowflakeIdWorker snowflakeIdWorker;
    @Value("${server.workerId}")
    private long workerId;
    @Value("${server.dataCenterId}")
    private long dataCenterId;

    public SnowflakeIdWorker getSnowflakeIdWorker() {
        if (snowflakeIdWorker == null) {
            synchronized (SnowflakeIdWorker.class) {
                if (snowflakeIdWorker == null) {
                    snowflakeIdWorker = new SnowflakeIdWorker(workerId, dataCenterId);
                }
            }
        }
        return snowflakeIdWorker;
    }
}
