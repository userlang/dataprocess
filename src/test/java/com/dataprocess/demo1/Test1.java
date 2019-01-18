package com.dataprocess.demo1;

import com.dataprocess.core.handler.CoreHandler;
import com.dataprocess.core.interceptor.RollBackInterceptor;
import com.dataprocess.core.template.CoreTemplate;
import com.dataprocess.databases.bpm.login_info.LoginInfoES;
import com.dataprocess.demo2.MyMethodInterceptor;
import org.springframework.cglib.proxy.Enhancer;

import javax.el.MethodNotFoundException;
import java.lang.reflect.Method;

public class Test1 {
    public static void main(String[] args) {

//        HelloServiceImpl hsi=new HelloServiceImpl();
//

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

        //   proxy.insert();
        try{

            Method method  = proxy.getClass().getMethod("insert",Object.class);
            if(null==method){
                throw new MethodNotFoundException("  The method of this "+proxy.getClass()+" cannot be found   ");
            }
            method.invoke(proxy,111);


        }catch (Exception e){
            e.printStackTrace();
        }


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
