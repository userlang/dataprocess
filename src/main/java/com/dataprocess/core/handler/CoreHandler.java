package com.dataprocess.core.handler;

import com.dataprocess.core.interceptor.ProxyAfter;
import com.dataprocess.core.interceptor.ProxyBefore;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Core  proxy invocation
 * @Author junnan.lang
 * @Date 2022/1/19 11:21
 * @Version 1.0
 */
public class CoreHandler implements MethodInterceptor {
    private List<ProxyBefore> proxyBeforeList;
    private List<ProxyAfter> proxyAfterList;
    public static final Map<String,Integer> METHOD=new HashMap<>();
    static{
        METHOD.put("insert",1);
        METHOD.put("update",1);
        METHOD.put("delete",1);
    }

    public CoreHandler(List<ProxyBefore> proxyBeforeList, List<ProxyAfter> proxyAfterList) {
        this.proxyBeforeList = proxyBeforeList;
        this.proxyAfterList = proxyAfterList;

    }
    public CoreHandler() {


    }


    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(null!=METHOD.get(method.getName())){
            if(null!=proxyBeforeList&&proxyBeforeList.size()>0){
                for (ProxyBefore before:  proxyBeforeList) {
                    before.before(objects);
                }
            }
        }


        Invocation invocation=new Invocation(obj,method,methodProxy,objects);
        Object object= invocation.invokeProxy();
        if(null!=METHOD.get(method.getName())){
            if(null!=proxyAfterList&&proxyAfterList.size()>0){
                for (ProxyAfter after:  proxyAfterList) {
                    after.after(object);
                }
            }
        }
        return object;
    }






}
