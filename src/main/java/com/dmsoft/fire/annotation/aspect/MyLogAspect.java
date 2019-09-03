package com.dmsoft.fire.annotation.aspect;

import com.alibaba.druid.support.json.JSONUtils;
import com.dmsoft.fire.annotation.MyLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author zhixin.huang
 * @date 2019/5/17 9:50
 */
@Aspect
@Component
public class MyLogAspect {

    private static Logger logger = LoggerFactory.getLogger(MyLogAspect.class);

    @Pointcut("@annotation(com.dmsoft.fire.annotation.MyLog)")
    public void annotationPointcut() {
    }

    @Around("annotationPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : args) {
            stringBuilder.append(JSONUtils.toJSONString(object));
        }
        Method method = signature.getMethod();
        method.setAccessible(true);
        String value = "";
        boolean write = false;
        if (method.isAnnotationPresent(MyLog.class)) {
            MyLog annotation = method.getAnnotation(MyLog.class);
            value = annotation.value();
            write = annotation.write();
        }
        logger.info(value + "-请求IP:[{}]，请求URL:[{}]，请求参数:[{}]", remoteAddr, requestURI, stringBuilder.toString());

        if(write) {

        }

        return joinPoint.proceed();
    }
}
