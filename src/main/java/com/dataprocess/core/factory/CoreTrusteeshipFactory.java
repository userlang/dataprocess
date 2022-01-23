package com.dataprocess.core.factory;

import com.dataprocess.core.common.ClazzUtils;
import com.dataprocess.core.common.CoreConst;
import com.dataprocess.core.handler.CoreHandler;
import com.dataprocess.core.subject.CoreSubject;
import com.dataprocess.core.subject.SubscribeSubject;
import com.dataprocess.core.template.ChainTemplate;
import com.dataprocess.core.template.ObserverTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.proxy.Enhancer;

import java.util.ArrayList;
import java.util.List;
/**
*  Trusteeship plant
 * @Author junnan.lang
 * @Date 2022/1/19 11:21
 * @Version 1.0
 */
public class CoreTrusteeshipFactory {
    private final static Logger logger = LoggerFactory.getLogger(CoreTrusteeshipFactory.class);

    private CoreTrusteeshipFactory(){}


    public List<Object> createTableListClass(String dataBase, String table) throws ClassNotFoundException, NoSuchMethodException {
        List<Object> objectList= ClazzUtils.getCalsss(
                CoreConst.EXECUTE_PACKAGE_PREFIX+dataBase+"."+table);
        return objectList;
    }
    public ChainTemplate  createChainTemplate(List<Object> objectList) throws ClassNotFoundException, NoSuchMethodException {
        List<ChainTemplate> coreHandlers =new ArrayList<>();

        if(null!=objectList&&objectList.size()>0){
            for (int x=0;x<objectList.size();x++){
                if(objectList.get(x) instanceof ChainTemplate){
                    ChainTemplate proxy=(ChainTemplate) createProxyCoreHandler(objectList.get(x));
                    BeanUtils.copyProperties(objectList.get(x), proxy);
                    coreHandlers.add(proxy);
                }
            }
            return assembleChain(coreHandlers);
        }
        return null;
    }
    public CoreSubject createObserverTemplate(List<Object> objectList){
        CoreSubject subject;
        if(null!=objectList&&objectList.size()>0){
            subject= new SubscribeSubject();
            for (int x=0;x<objectList.size();x++){
                if(objectList.get(x) instanceof ObserverTemplate){
                    subject.attach((ObserverTemplate) objectList.get(x));
                }
            }
            return subject;
        }
        return null;
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

    private Object createProxyCoreHandler(Object object){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(new CoreHandler());
       return enhancer.create();

    }

    private static volatile CoreTrusteeshipFactory instance;


    /**
     *
     * @return
     */
    public static CoreTrusteeshipFactory getInstance() {

        if (instance == null) {

            synchronized (CoreTrusteeshipFactory.class) {

                if (instance == null) {

                    instance = new CoreTrusteeshipFactory();
                }
            }
        }
        return instance;
    }
}
