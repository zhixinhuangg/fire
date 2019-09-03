package com.dmsoft.fire.annotation.aspect;

import com.dmsoft.fire.annotation.ExtCacheable;
import com.dmsoft.fire.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhixin.huang
 * @date 2019/4/9 17:30
 */
@Aspect
@Component
public class CacheAspect {

    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取key中#p0中的参数名称
     *
     * @param key 注解key值
     * @return 参数列表
     */
    private static List<String> getKeyParsList(String key) {
        List<String> listPar = new ArrayList<>();
        String split = "#";
        if (key.contains(split)) {
            int plusIndex = key.substring(key.indexOf(split)).indexOf("+");
            int indexNext = 0;
            String parName;
            int indexPre = key.indexOf(split);
            if (plusIndex > 0) {
                indexNext = key.indexOf(split) + key.substring(key.indexOf(split)).indexOf("+");
                parName = key.substring(indexPre, indexNext);
            } else {
                parName = key.substring(indexPre);
            }
            listPar.add(parName.trim());
            key = key.substring(indexNext + 1);
            if (key.contains(split)) {
                listPar.addAll(getKeyParsList(key));
            }
        }
        return listPar;
    }

    @Pointcut("@annotation(com.dmsoft.fire.annotation.ExtCacheable)")
    public void annotationPointcut() {
    }

    @Around("annotationPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获得当前访问的class
        Class<?> clazz = joinPoint.getTarget().getClass();

        //获得访问的方法名
        String methodName = joinPoint.getSignature().getName();

        //获得方法的参数的类型
        Class<?>[] argClazz = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();

        Object[] args = joinPoint.getArgs();

        String key = "";

        int expireTime = 1800;

        try {
            Method method = clazz.getMethod(methodName, argClazz);
            method.setAccessible(true);
            //判断是否存在@ExtCacheable注解
            if (method.isAnnotationPresent(ExtCacheable.class)) {
                ExtCacheable annotation = method.getAnnotation(ExtCacheable.class);
                key = getRedisKey(args, annotation);
                expireTime = getExpireTime(annotation);
            }

        } catch (Exception e) {
            throw new RuntimeException("redis缓存注解参数异常", e);
        }

        boolean hasKey = redisUtil.hasKey(key);

        if (hasKey) {
            return redisUtil.get(key);
        } else {
            //执行原方法（java反射执行method获取结果）
            Object res = joinPoint.proceed();
            redisUtil.set(key, res);
            redisUtil.expire(key, expireTime);

            return res;
        }
    }

    private int getExpireTime(ExtCacheable annotation) {
        return annotation.expireTime();
    }

    private String getRedisKey(Object[] args, ExtCacheable annotation) {
        String primalKey = annotation.key();
        //获取#p0...集合
        List<String> keyList = getKeyParsList(primalKey);
        for (String keyName : keyList) {
            int keyIndex = Integer.parseInt(keyName.toLowerCase().replace("#p", ""));
            Object parValue = args[keyIndex];
            primalKey = primalKey.replace(keyName, String.valueOf(parValue));
        }
        return primalKey.replace("+", "").replace("'", "");
    }
}
