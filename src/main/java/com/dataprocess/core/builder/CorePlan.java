package com.dataprocess.core.builder;

import com.dataprocess.core.subject.CoreSubject;
import com.dataprocess.core.template.LinkedTemplate;

public class CorePlan {

    private CoreSubject coreSubject;

    private LinkedTemplate linkedTemplate;


    public LinkedTemplate getChainTemplate() {
        return linkedTemplate;
    }

    public void setChainTemplate(LinkedTemplate linkedTemplate) {
        this.linkedTemplate = linkedTemplate;
    }

    public CoreSubject getCoreSubject() {
        return coreSubject;
    }

    public void setCoreSubject(CoreSubject coreSubject) {
        this.coreSubject = coreSubject;
    }
}
