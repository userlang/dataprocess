package com.dataprocess.core.builder;

import com.dataprocess.core.common.ClazzUtils;
import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.listener.DataProcessContext;
import com.dataprocess.core.listener.DataProcessRequestAware;
import com.dataprocess.core.template.LinkedTemplate;
import com.dataprocess.core.template.ListenerTemplate;

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
    public void buildListenerTemplate() throws ClassNotFoundException, NoSuchMethodException {
        DataProcessContext subject;
        List<Object> objectList=createTableListClass();

        if(null!=objectList&&objectList.size()>0){
            subject= new DataProcessRequestAware();
            for (int x=0;x<objectList.size();x++){
                if(objectList.get(x) instanceof ListenerTemplate){
                    subject.addListener((ListenerTemplate) objectList.get(x));
                }
            }
            super.plan.setDataProcessListener(subject);
        }

    }

    @Override
    public void buildChainTemplate() throws ClassNotFoundException, NoSuchMethodException {
        List<LinkedTemplate> coreHandlers =new ArrayList<>();
        List<Object> objectList=createTableListClass();
        if(null!=objectList&&objectList.size()>0){
            for (int x=0;x<objectList.size();x++){
                if(objectList.get(x) instanceof LinkedTemplate){
                    coreHandlers.add((LinkedTemplate) objectList.get(x));
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
}
