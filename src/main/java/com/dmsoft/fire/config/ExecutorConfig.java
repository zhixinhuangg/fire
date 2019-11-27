package com.dmsoft.fire.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhixin.huang
 * @date 2019/4/8 17:35
 */
@EnableAsync
@Configuration
public class ExecutorConfig {

    @Value("${threadPool.corePoolSize}")
    private int corePoolSize;
    @Value("${threadPool.maximumPoolSize}")
    private int maximumPoolSize;
    @Value("${threadPool.keepAliveTime}")
    private int keepAliveTime;
    @Value("${threadPool.queueSize}")
    private int queueSize;

    @Bean
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maximumPoolSize);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setQueueCapacity(queueSize);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.setThreadNamePrefix("async-service-");
        return executor;
    }
}
