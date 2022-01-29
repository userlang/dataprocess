package com.dataprocess.core.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component

public class Properties {

    public static boolean isOPEN_PROXY;


    @Value("${open.proxy}")
    public void setOPEN_PROXY(boolean OPEN_PROXY) {

        this.isOPEN_PROXY=OPEN_PROXY;
    }


}
