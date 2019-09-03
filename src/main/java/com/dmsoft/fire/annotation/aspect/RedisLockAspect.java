package com.dmsoft.fire.annotation.aspect;

import com.dmsoft.fire.annotation.RedisLock;
import com.dmsoft.fire.util.RedisUtil;
import com.dmsoft.fire.util.SnowflakeComponent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author zhixin.huang
 * @date 2019/08/25 20:40
 */
@Aspect
@Component
public class RedisLockAspect {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private SnowflakeComponent snowflakeComponent;

    private static Logger logger = LoggerFactory.getLogger(RedisLockAspect.class);

    @Pointcut("@annotation(com.dmsoft.fire.annotation.RedisLock)")
    public void annotationPointcut() {
    }

    @Around("annotationPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(RedisLock.class)) {
            RedisLock annotation = method.getAnnotation(RedisLock.class);
            String key = annotation.key();
            long expire = annotation.expire();
            String s = snowflakeComponent.getSnowflakeIdWorker().nextId();
            logger.info("开始获取锁");
            boolean b = redisUtil.setNXWithExpire(key, s, expire);
            if (b) {
                joinPoint.proceed();
                String o = (String) redisUtil.get(key);
                if (s.equals(o)) {
                    redisUtil.del(key);
                }
            } else {
                boolean b1 = false;
                int i = 0;
                //设置为重试十次之后，还是没有获取分布式锁后，退出
                while (!b1 && i < 10) {
                    Thread.sleep(500);
                    b1 = redisUtil.setNXWithExpire(key, s, expire);
                    i++;
                }
                if (b1) {
                    joinPoint.proceed();
                    String o = (String) redisUtil.get(key);
                    if (s.equals(o)) {
                        redisUtil.del(key);
                    }
                }
            }
        }
        return null;
    }
}
