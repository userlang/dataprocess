package com.dataprocess.core.handler;

import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
/**
 * Core Invocation
 * @Author junnan.lang
 * @Date 2022/1/19 11:21
 * @Version 1.0
 */
public class Invocation {
    private Object target;

    private Method method;

    private MethodProxy methodProxy;

    private Object[] argsProxy;
    private Object args;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public void setMethodProxy(MethodProxy methodProxy) {
        this.methodProxy = methodProxy;
    }

    public Object[] getArgs() {
        return argsProxy;
    }

    public void setArgs(Object[] args) {
        this.argsProxy = args;
    }

    public Invocation(Object target, Method method, MethodProxy methodProxy, Object[] argsProxy) {
        this.target = target;
        this.method = method;
        this.methodProxy = methodProxy;
        this.argsProxy = argsProxy;
    }
    public Invocation(Object target, Method method,  Object args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }
    public Object invokeProxy() throws Throwable {
        return  methodProxy.invokeSuper(target,argsProxy);

    }
    public Object invoke() throws Throwable {
        return  method.invoke(target,args);

    }


}
