package com.dataprocess.core.handler;

import java.lang.reflect.Method;

public class RollBackHandler implements InterceptHandler   {

    private String ROLLBACKSUFFIX="RollBack";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result=null;
        try{
            result=invocation.invoke();
        }catch (Exception e){
            String rollbackMethodName= invocation.getMethod().getName()+ROLLBACKSUFFIX;
            Method rollbackMethod= invocation.getTarget().getClass().getMethod(rollbackMethodName,Object.class);
            invocation.setMethod(rollbackMethod);
            invocation.invoke();
            e.printStackTrace();
        }
        return result;
    }
}
