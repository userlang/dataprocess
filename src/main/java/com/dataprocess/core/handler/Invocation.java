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

    private Object[] args;

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
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Invocation(Object target, Method method, MethodProxy methodProxy, Object[] args) {
        this.target = target;
        this.method = method;
        this.methodProxy = methodProxy;
        this.args = args;
    }

    public Object invoke() throws Throwable {

        try{
          return  methodProxy.invokeSuper(target,args);
        }catch (Exception e){
            String methodName=this.method.getName()+ROLLBACK;
            Method rollBackMethod= null;
            try {
                rollBackMethod = this.target.getClass().getMethod(methodName,Object.class);
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
            if(null!=rollBackMethod){
               this.method=rollBackMethod;
                try {
                    method.invoke(target,args);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return null;
    }


    private static final String ROLLBACK="RollBack";

}
