package com.dataprocess.databases.bpm.login_info;

import com.dataprocess.core.template.CoreTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoginInfoEs extends CoreTemplate {

    @Override
    public Object insert(Object obj) {
        System.out.println("正在执行 es 得添加");
        return null;
    }

    @Override
    public Object update(Object obj) {
        System.out.println("正在执行 es 得修改 开始");

       // CoreTemplate c= getNext();
        //System.out.println(c);
      //  c.update(obj);

        getNext().update(obj);
        System.out.println("正在执行 es 得修改 结束");
        return null;
    }

    @Override
    public Object delete(Object obj) {
        System.out.println("正在执行 es 得删除");
        return null;
    }

    @Override
    public void insertRollBack(Object obj) {
        System.out.println("正在执行 es 得添加  异常");
    }

    @Override
    public void updateRollBack(Object obj) {
        System.out.println("正在执行 es 得修改  异常");
    }

    @Override
    public void deleteRollBack(Object obj) {
        System.out.println("正在执行 es 得删除  异常");
    }
}
