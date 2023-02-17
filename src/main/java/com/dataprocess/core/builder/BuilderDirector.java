package com.dataprocess.core.builder;

public class BuilderDirector
{
    private CoreBuilder builder;

    public BuilderDirector(CoreBuilder builder) {
        this.builder = builder;
    }
    public CorePlan build(){
        try {
            builder.buildChainTemplate();
            builder.buildListenerTemplate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }finally {
            return builder.getResult();
        }
    }
}
