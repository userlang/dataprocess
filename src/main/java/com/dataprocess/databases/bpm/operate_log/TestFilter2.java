package com.dataprocess.databases.bpm.operate_log;

import com.dataprocess.core.template.CoreTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestFilter2 extends CoreTemplate {
    @Override
    public Object insert(Object obj) {

        System.out.println("正在执行 operate_log表的 test2 添加操作 开始");
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

    @Override
    public void insertRollBack(Object obj) {

    }

    @Override
    public void updateRollBack(Object obj) {

    }

    @Override
    public void deleteRollBack(Object obj) {

    }
}
