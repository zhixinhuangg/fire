package com.dmsoft.fire.config;

import com.dmsoft.fire.FireApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * @author zhixin.huang
 * @date 2019/4/11 14:26
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FireApplication.class})
public class ExecutorTest {
    @Resource
    private Executor asyncServiceExecutor;

    @Test
    public void executorTest() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            asyncServiceExecutor.execute(() -> {
                log.info("第一次正在执行第" + finalI + "个任务");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("第一次正在执行第" + finalI + "个任务完成");
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            asyncServiceExecutor.execute(() -> {
                log.info("第二次正在执行第" + finalI + "个任务");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                log.info("第二次执行第" + finalI + "个任务完成");
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        asyncServiceExecutor.execute(() -> {
            log.info("最后一次执行任务");
        });
    }
}
