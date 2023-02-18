package com.dataprocess.core.listener;

import com.dataprocess.core.handler.Invocation;
import com.dataprocess.core.template.ListenerTemplate;

import java.util.ArrayList;
import java.util.List;

public class DataProcessRequestAware implements DataProcessContext {
    private List<ListenerTemplate> listenerTemplate=new ArrayList<>();



    public void addListener(ListenerTemplate obServer){
        listenerTemplate.add( obServer);

    }

    public void delListener(ListenerTemplate obServer){
        listenerTemplate.remove(obServer);
    }


    public void notify(Object data,String type){
        if(null!=listenerTemplate&&listenerTemplate.size()>0){
            for (ListenerTemplate item: listenerTemplate) {
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
