package com.dataprocess.core.interceptor;

import com.dataprocess.core.handler.Invocation;

public interface HandlerInterceptor {

    public Object interceptor(Invocation invocation) throws Throwable;
}
