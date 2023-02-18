package com.dataprocess.core.builder;

import com.dataprocess.core.common.ClazzUtils;
import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.common.SpringUtils;
import com.dataprocess.core.handler.CoreHandler;
import com.dataprocess.core.interceptor.ProxyAfter;
import com.dataprocess.core.interceptor.ProxyBefore;
import com.dataprocess.core.listener.DataProcessContext;
import com.dataprocess.core.listener.DataProcessRequestAware;
import com.dataprocess.core.template.LinkedTemplate;
import com.dataprocess.core.template.ListenerTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.proxy.Enhancer;

import java.util.*;

public class ProxyBuilder extends CoreBuilder {


    private String table;

    private String database;

    public ProxyBuilder(String database, String table) {
        this.table=table;
        this.database=database;
    }

    @Override
    public void buildListenerTemplate() throws ClassNotFoundException, NoSuchMethodException {
        DataProcessContext listener;
        List<Object> objectList=createTableListClass();

        if(null!=objectList&&objectList.size()>0){
            listener= new DataProcessRequestAware();
            for (int x=0;x<objectList.size();x++){
                if(objectList.get(x) instanceof ListenerTemplate){
                    ListenerTemplate proxy=(ListenerTemplate) createProxyCoreHandler(objectList.get(x));
                    BeanUtils.copyProperties(objectList.get(x), proxy);
                    listener.addListener(proxy);

                }
            }
            super.plan.setDataProcessListener(listener);
        }

    }

    @Override
    public void buildChainTemplate() throws ClassNotFoundException, NoSuchMethodException {
        List<LinkedTemplate> coreHandlers =new ArrayList<>();
        List<Object> objectList=createTableListClass();

          if(null!=objectList&&objectList.size()>0){
              for (int x=0;x<objectList.size();x++){
                  if(objectList.get(x) instanceof LinkedTemplate){
                      LinkedTemplate proxy=(LinkedTemplate) createProxyCoreHandler(objectList.get(x));
                      BeanUtils.copyProperties(objectList.get(x), proxy);
                      coreHandlers.add(proxy);

                  }
              }
              super.plan.setLinkedTemplate(assembleChain(coreHandlers));
          }
    }
    /**
     * Assembly responsibility chain
     * @param chainTemplates
     * @return
     */
    private LinkedTemplate assembleChain(List<LinkedTemplate> chainTemplates) {
        if(null!=chainTemplates&&chainTemplates.size()>0){
            for (int i=0;i<chainTemplates.size()-1;i++)
            {
                int j=i+1;
                chainTemplates.get(i).setSuccessor(chainTemplates.get(j));

            }
            return chainTemplates.get(0);
        }
        return null;
    }
    public List<Object> createTableListClass() throws ClassNotFoundException, NoSuchMethodException {
        List<Object> objectList=null;
        if(null==objectList){
            objectList= ClazzUtils.getCalsss(
                    CoreConst.EXECUTE_PACKAGE_PREFIX+this.database+"."+this.table);
        }
        return objectList;
    }
    private Object createProxyCoreHandler(Object proxyObj){
     try {
         Enhancer enhancer = new Enhancer();
         enhancer.setSuperclass(proxyObj.getClass());
         enhancer.setCallback(new CoreHandler(getProxyBefore(),getProxyAfter()));
         return enhancer.create();
     }catch (Exception e){
         e.printStackTrace();
     }
     return null;
    }

    public List<ProxyBefore> getProxyBefore(){
        List<ProxyBefore> list=SpringUtils.getBeansOfType(ProxyBefore.class);
        return list;
    }
    public List<ProxyAfter> getProxyAfter(){

        List<ProxyAfter> list=SpringUtils.getBeansOfType(ProxyAfter.class);
        return list;
    }
}
