package com.dataprocess.core.handler;

import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.List;

public class CoreProxyInvocation implements InvocationHandler {

    private Object target;

    private InterceptHandler handler;

    public CoreProxyInvocation(Object target, InterceptHandler handler) {
        this.target = target;
        this.handler = handler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation=new Invocation(target,method,args);
        return handler.intercept(invocation);
    }

}
