package com.dataprocess.core.listener;

import com.dataprocess.core.template.ListenerTemplate;

public interface DataProcessContext {
    /**
     * 增加订阅者
     *
     */
    public void addListener(ListenerTemplate obServer);
    /**
     * 删除订阅者x
     */
    public void delListener(ListenerTemplate obServer);

    /**
     * 通知订阅者更新x消息
     */
    public void notify(Object data,String type);
}
