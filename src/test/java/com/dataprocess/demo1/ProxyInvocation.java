package com.dataprocess.demo1;

import com.dataprocess.demo2.Handler;
import com.dataprocess.demo2.Invocation;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 代理类
 */
public class ProxyInvocation implements InvocationHandler {

    private Object target;

    private Handler handler;

    public ProxyInvocation(Object target,Handler handler) {
        this.target = target;
        this.handler=handler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation=new Invocation(target,method,args);

        return handler.intercept(invocation);
    }
    public static Object getProxyObject(Object target,Handler handler){


        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new ProxyInvocation(target,handler));
    }


}
