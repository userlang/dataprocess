package com.dataprocess.core.subject;

import com.dataprocess.core.handler.Invocation;
import com.dataprocess.core.template.ObserverTemplate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SubscribeSubject implements  CoreSubject{
    List<ObserverTemplate> observerTemplates=new ArrayList<>();



    public void attach(ObserverTemplate obServer){
        observerTemplates.add( obServer);

    }

    public void detach(ObserverTemplate obServer){
        observerTemplates.remove(obServer);
    }


    public void notify(Object data,String type){
        if(null!=observerTemplates&&observerTemplates.size()>0){
            for (ObserverTemplate item: observerTemplates) {
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
