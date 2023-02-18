package com.dataprocess.core.listener;

import com.dataprocess.core.template.EventTemplateListener;

public interface DataProcessListener {
    /**
     * 增加订阅者
     *
     */
    public void addListener(EventTemplateListener obServer);
    /**
     * 删除订阅者x
     */
    public void delListener(EventTemplateListener obServer);

    /**
     * 通知订阅者更新x消息
     */
    public void notify(Object data,String type);
}
