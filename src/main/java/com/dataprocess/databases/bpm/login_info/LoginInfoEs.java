package com.dataprocess.databases.bpm.login_info;

import com.dataprocess.core.template.ChainTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoginInfoEs extends ChainTemplate {

    @Override
    public Object insert(Object obj) {
        Object result=null;
        System.out.println("正在执行LoginInfoEs 得添加 开始");
        result=getNext().insert(obj);
        System.out.println("正在执行LoginInfoEs 得添加 结束");
        return result;
    }

    @Override
    public Object update(Object obj) {
        Object result=null;
        System.out.println("正在执行LoginInfoEs 得修改 开始");
        result=getNext().insert(obj);
        System.out.println("正在执行LoginInfoEs 得修改 结束");
        return result;
    }

    @Override
    public Object delete(Object obj) {
        Object result=null;
        System.out.println("正在执行LoginInfoEs 得删除 开始");
        result=getNext().insert(obj);
        System.out.println("正在执行LoginInfoEs 得删除 结束");
        return result;
    }

    @Override
    public void insertRollBack(Object obj) {
        System.out.println("正在执行 正在执行LoginInfoEs 得添加  异常");
    }

    @Override
    public void updateRollBack(Object obj) {
        System.out.println("正在执行 正在执行LoginInfoEs 得修改  异常");
    }

    @Override
    public void deleteRollBack(Object obj) {
        System.out.println("正在执行 正在执行LoginInfoEs 得删除  异常");
    }
}
