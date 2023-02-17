package com.dataprocess.databases.bpm.merchant;

import com.dataprocess.core.template.LinkedTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author junnan.lang
 * @Date 2022/1/19 15:00
 * @Version 1.0
 */
@Component
public class MerchantES extends LinkedTemplate {

    @Override
    public Object insertData(Object obj) {
        System.out.println("正在执行商户es添加");
        return null;
    }

    @Override
    public Object updateData(Object obj) {
        System.out.println("正在执行商户es修改");
        getNext().update(obj);
        return null;
    }

    @Override
    public Object deleteData(Object obj) {
        System.out.println("正在执行商户es删除");
        return null;
    }

    @Override
    public void insertDataRollBack(Object obj) {
        System.out.println("正在执行商户es添加回滚");
    }

    @Override
    public void updateDataRollBack(Object obj) {
        System.out.println("正在执行商户es修改回滚");
    }

    @Override
    public void deleteDataRollBack(Object obj) {
        System.out.println("正在执行商户es删除回滚");
    }
}
