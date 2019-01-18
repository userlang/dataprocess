package com.dataprocess.core.handler;

import com.dataprocess.core.interceptor.HandlerInterceptor;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CoreHandler implements MethodInterceptor {

    private HandlerInterceptor handlerInterceptor;

    public CoreHandler(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Invocation invocation=new Invocation(obj,method,methodProxy,objects);
        return handlerInterceptor.interceptor(invocation);
    }


}
