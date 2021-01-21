package com.example.urlkeygenerator.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CustomAsyncExceptionHandler
        implements AsyncUncaughtExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        logger.error("Unexpected asynchronous exception at : "
                + method.getDeclaringClass().getName() + "." + method.getName(), ex);
    }

}

