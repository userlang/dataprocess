package com.dataprocess.databases.bpm.merchant;

import com.dataprocess.core.template.ChainTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author junnan.lang
 * @Date 2022/1/19 15:01
 * @Version 1.0
 */
@Component
public class MerchantRedis extends ChainTemplate {

    @Override
    public Object insert(Object obj) {
        System.out.println("正在执行商户redis添加");
        return null;
    }

    @Override
    public Object update(Object obj) {
        System.out.println("正在执行商户redis修改");
        return null;
    }

    @Override
    public Object delete(Object obj) {
        System.out.println("正在执行商户redis删除");
        return null;
    }

    @Override
    public void insertRollBack(Object obj) {
        System.out.println("正在执行商户添加redis回滚");
    }

    @Override
    public void updateRollBack(Object obj) {
        System.out.println("正在执行商户修改redis回滚");
    }

    @Override
    public void deleteRollBack(Object obj) {
        System.out.println("正在执行商户删除redis回滚");
    }
}
