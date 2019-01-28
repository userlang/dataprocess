package com.dataprocess.core.builder;

import com.dataprocess.core.subject.CoreSubject;
import com.dataprocess.core.template.ChainTemplate;

public class CorePlan {

    private CoreSubject coreSubject;

    private ChainTemplate chainTemplate;


    public ChainTemplate getChainTemplate() {
        return chainTemplate;
    }

    public void setChainTemplate(ChainTemplate chainTemplate) {
        this.chainTemplate = chainTemplate;
    }

    public CoreSubject getCoreSubject() {
        return coreSubject;
    }

    public void setCoreSubject(CoreSubject coreSubject) {
        this.coreSubject = coreSubject;
    }
}
