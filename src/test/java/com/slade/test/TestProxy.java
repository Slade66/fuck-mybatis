package com.slade.test;


import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {

    interface Logger {
        void log(String msg);
    }

    class DbLogger implements Logger {

        @Override
        public void log(String msg) {
            System.out.println("msg = " + msg);
        }
    }

    class DbLoggerProxyHandler implements InvocationHandler {

        Logger logger;

        public DbLoggerProxyHandler(Logger logger) {
            this.logger = logger;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before log");

            Object result = method.invoke(logger, args);

            System.out.println("after log");
            return result;
        }
    }

    @Test
    public void test() {
        Logger realLogger = new DbLogger();
        Logger proxyLogger = (Logger) Proxy.newProxyInstance(realLogger.getClass().getClassLoader(), new Class[]{Logger.class}, new DbLoggerProxyHandler(realLogger));
        proxyLogger.log("msg");
    }
}
