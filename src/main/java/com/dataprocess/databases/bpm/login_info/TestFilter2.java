package com.dataprocess.databases.bpm.login_info;

import com.dataprocess.core.handler.CoreHandler;
import org.springframework.stereotype.Component;

@Component
public class TestFilter2 extends CoreHandler {
    @Override
    public Object insert(Object obj) {
        System.out.println("正在执行 login_info下的test2  开始 参数 "+obj);


        return null;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public Object delete(Object obj) {
        return null;
    }
}
