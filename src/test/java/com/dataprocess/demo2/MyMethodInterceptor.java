package com.dataprocess.demo2;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        System.out.println("before method run..." );
        Object result = methodProxy.invokeSuper(o, objects);
//        System.out.println("after method run..." );
        return result;
    }
}
