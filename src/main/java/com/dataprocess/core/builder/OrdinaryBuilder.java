package com.dataprocess.core.builder;

import com.dataprocess.core.common.ClazzUtils;
import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.subject.CoreSubject;
import com.dataprocess.core.subject.SubscribeSubject;
import com.dataprocess.core.template.ChainTemplate;
import com.dataprocess.core.template.ObserverTemplate;

import java.util.ArrayList;
import java.util.List;

public class OrdinaryBuilder extends CoreBuilder {


    private String table;

    private String database;

    public OrdinaryBuilder(String database, String table) {
        this.table=table;
        this.database=database;
    }

    @Override
    public void buildObserverTemplate() throws ClassNotFoundException, NoSuchMethodException {
        CoreSubject subject;
        List<Object> objectList=createTableListClass();

        if(null!=objectList&&objectList.size()>0){
            subject= new SubscribeSubject();
            for (int x=0;x<objectList.size();x++){
                if(objectList.get(x) instanceof ObserverTemplate){
                    subject.attach((ObserverTemplate) objectList.get(x));
                }
            }
            super.plan.setCoreSubject(subject);
        }

    }

    @Override
    public void buildChainTemplate() throws ClassNotFoundException, NoSuchMethodException {
        List<ChainTemplate> coreHandlers =new ArrayList<>();
        List<Object> objectList=createTableListClass();
        if(null!=objectList&&objectList.size()>0){
            for (int x=0;x<objectList.size();x++){
                if(objectList.get(x) instanceof ChainTemplate){
                    coreHandlers.add((ChainTemplate) objectList.get(x));
                }
            }
             super.plan.setChainTemplate(assembleChain(coreHandlers));
        }

    }
    /**
     * Assembly responsibility chain
     * @param chainTemplates
     * @return
     */
    private ChainTemplate assembleChain(List<ChainTemplate> chainTemplates) {
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
}
