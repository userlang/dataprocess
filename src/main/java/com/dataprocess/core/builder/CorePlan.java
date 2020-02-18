package com.dataprocess.core.builder;

import com.dataprocess.core.listener.DataProcessListener;
import com.dataprocess.core.template.LinkedTemplate;

public class CorePlan {

    private DataProcessListener dataProcessListener;

    private LinkedTemplate linkedTemplate;

    public LinkedTemplate getLinkedTemplate() {
        return linkedTemplate;
    }

    public void setLinkedTemplate(LinkedTemplate linkedTemplate) {
        this.linkedTemplate = linkedTemplate;
    }

    public DataProcessListener getDataProcessListener() {
        return dataProcessListener;
    }

    public void setDataProcessListener(DataProcessListener dataProcessListener) {
        this.dataProcessListener = dataProcessListener;
    }
}
