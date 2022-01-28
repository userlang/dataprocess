package com.dataprocess.core.builder;

public abstract class CoreBuilder {

    protected CorePlan plan=new CorePlan();

    public abstract void buildObserverTemplate() throws ClassNotFoundException, NoSuchMethodException;
    public abstract void buildChainTemplate() throws ClassNotFoundException, NoSuchMethodException;
    public CorePlan getResult(){
        return plan;
    }

}
