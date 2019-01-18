package com.dataprocess.core.interceptor;

import com.dataprocess.core.handler.Invocation;
import com.dataprocess.core.template.CoreTemplate;

import java.lang.reflect.Method;

public class RollBackInterceptor implements HandlerInterceptor  {


    @Override
    public Object interceptor(Invocation invocation) throws Throwable  {

        try{
           return invocation.invoke();
        }catch (Exception e){
            String methodName=invocation.getMethod().getName()+ROLLBACK;
            Method rollBackMethod= null;
            try {
                rollBackMethod = invocation.getTarget().getClass().getMethod(methodName,Object.class);
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
            if(null!=rollBackMethod){
                invocation.setMethod(rollBackMethod);
                try {
                    invocation.rollback();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }finally {
            return null;
        }

    }
    private static final String ROLLBACK="RollBack";
}
