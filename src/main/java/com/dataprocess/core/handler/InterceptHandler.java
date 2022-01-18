package com.dataprocess.core.handler;


public interface InterceptHandler {

    /**
     This is a dynamic proxy interceptor. It needs and must have an implementation class
     * @param invocation
     * @return
     * @throws Throwable
     */
    Object intercept(Invocation invocation)throws Throwable;
}
