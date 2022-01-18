package com.dataprocess.demo1;

import com.dataprocess.demo2.CglibProxy;
import com.dataprocess.demo2.RollBackHandler;
import com.dataprocess.demo3.MyMethodInterceptor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {

//        HelloServiceImpl hsi=new HelloServiceImpl();
//
//      //  ProxyInvocation invocation=new ProxyInvocation(hsi,new RollBackHandler());
//
//        HelloService hs=(HelloService) ProxyInvocation.getProxyObject(hsi,new RollBackHandler());
//
////      Proxy.newProxyInstance(hsi.getClass().getClassLoader(),hsi.getClass().getInterfaces(),invocation);
//      //  hs.insert(11);
//        hs.update(12);
       Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(HelloServiceImpl.class);

       // 设置enhancer的回调对象
        enhancer.setCallback(new MyMethodInterceptor());
       // 创建代理对象
        HelloService proxy= (HelloService)enhancer.create();
        // 通过代理对象调用目标方法
        HelloServiceImpl helloService= new HelloServiceImpl();
        helloService.setI(1111);
        BeanUtils.copyProperties(helloService, proxy);
        proxy.insert();

//        HelloServiceImpl helloService=new HelloServiceImpl();
//         helloService.setI(1);
//        HelloService h=helloService;
//        h.insert();;
//        HelloServiceImpl helloService2=new HelloServiceImpl();
//        BeanUtils.copyProperties(h, helloService2);
//
//        helloService2.insert();

    }



}
