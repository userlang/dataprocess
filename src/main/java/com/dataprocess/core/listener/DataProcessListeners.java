package com.dataprocess.core.listener;

import com.dataprocess.core.handler.Invocation;
import com.dataprocess.core.template.EventTemplateListener;

import java.util.ArrayList;
import java.util.List;

public class DataProcessListeners implements DataProcessListener {
    private List<EventTemplateListener> listenerTemplate=new ArrayList<>();



    public void addListener(EventTemplateListener obServer){
        listenerTemplate.add( obServer);

    }

    public void delListener(EventTemplateListener obServer){
        listenerTemplate.remove(obServer);
    }


    public void notify(Object data,String type){
        if(null!=listenerTemplate&&listenerTemplate.size()>0){
            for (EventTemplateListener item: listenerTemplate) {
                try{
                    Invocation invocation=new Invocation(item,item.getClass().getMethod(type.toLowerCase(),Object.class),data);
                    invocation.invoke();
                }catch (Throwable e){
                    e.printStackTrace();
                }
            }
        }
    }


}
