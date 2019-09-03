package com.dmsoft.fire.openapi.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.concurrent.Executor;

/**
 * @author zhixin.huang
 * @date 2019/4/12 9:54
 */
@Slf4j
@RestController
@RequestMapping("api/v1/executor")
public class ExecutorFacade {


    @Resource
    private Executor asyncServiceExecutor;

    @GetMapping("/start")
    public void start() {
        ThreadLocal<String> s = ThreadLocal.withInitial(() -> "aaa");
        ThreadLocal<String> s1 = ThreadLocal.withInitial(() -> "aaa");
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            asyncServiceExecutor.execute(() -> {
                log.info("第一次正在执行第" + finalI + "个任务，当前参数：" + s.get() + "，" + s1.get());
                s.set("aaa：" + finalI);
                s1.set("bbb：" + finalI);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("第一次正在执行第" + finalI + "个任务完成，结果为：" + s.get() + "，" + s1.get());
                s.remove();
                s1.remove();
                log.info("第一次正在执行第" + finalI + "个任务完成，结果为：" + s.get() + "，" + s1.get());
            });
        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < 3; i++) {
//            int finalI = i;
//            asyncServiceExecutor.execute(() -> {
//                log.info("第二次正在执行第" + finalI + "个任务");
////                try {
////                    Thread.sleep(10000);
////                } catch (InterruptedException e) {
////
////                    e.printStackTrace();
////                }
//                log.info("第二次执行第" + finalI + "个任务完成");
//            });
//        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        asyncServiceExecutor.execute(() -> {
//            log.info("最后一次执行任务");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("最后一次执行任务完成");
//        });
    }
}
