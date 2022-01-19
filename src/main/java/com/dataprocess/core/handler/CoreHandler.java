package com.dataprocess.core.handler;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
/**
 * Resolve processing policy factory
 * @Author junnan.lang
 * @Date 2022/1/19 11:21
 * @Version 1.0
 */
public class CoreHandler implements MethodInterceptor {



    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Invocation invocation=new Invocation(obj,method,methodProxy,objects);
        return invocation.invoke();
    }






}
