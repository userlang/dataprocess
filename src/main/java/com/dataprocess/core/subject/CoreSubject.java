package com.dataprocess.core.subject;

import com.dataprocess.core.template.ObserverTemplate;

public interface CoreSubject {
    /**
     * 增加订阅者
     *
     */
    public void attach(ObserverTemplate obServer);
    /**
     * 删除订阅者
     */
    public void detach(ObserverTemplate obServer);

    /**
     * 通知订阅者更新x消息
     */
    public void notify(Object data,String type);
}
