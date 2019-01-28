package com.dataprocess.interceptor;

import com.dataprocess.core.interceptor.ProxyBefore;
import org.springframework.stereotype.Component;

@Component
public class TestInterceptor implements ProxyBefore {
    @Override
    public void before(Object param) {
        System.out.println("前置拦截器");
    }
}
