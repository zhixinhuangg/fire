package com.dmsoft.fire.openapi.v1;

import com.dmsoft.fire.annotation.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhixin.huang
 * @date 2019/08/25 21:33
 */
@RestController
@RequestMapping("api/v1/test")
public class Test {

    private static Logger logger = LoggerFactory.getLogger(Test.class);

    @GetMapping("/redisLockTest")
    @RedisLock(key = "test")
    public void redisLockTest() {
        logger.info("方法开始执行");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("方法执行结束");
    }
}
