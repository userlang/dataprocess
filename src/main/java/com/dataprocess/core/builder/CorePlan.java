package com.dataprocess.core.builder;

import com.dataprocess.core.listener.DataProcessContext;
import com.dataprocess.core.template.LinkedTemplate;

public class CorePlan {

    private DataProcessContext dataProcessListener;

    private LinkedTemplate linkedTemplate;

    public LinkedTemplate getLinkedTemplate() {
        return linkedTemplate;
    }

    public void setLinkedTemplate(LinkedTemplate linkedTemplate) {
        this.linkedTemplate = linkedTemplate;
    }

    public DataProcessContext getDataProcessListener() {
        return dataProcessListener;
    }

    public void setDataProcessListener(DataProcessContext dataProcessListener) {
        this.dataProcessListener = dataProcessListener;
    }
}
