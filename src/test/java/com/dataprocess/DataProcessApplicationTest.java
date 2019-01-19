package com.dataprocess;

import com.dataprocess.core.factory.CoreStrategyFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class DataProcessApplicationTest {

    private CoreStrategyFactory executeFactory= CoreStrategyFactory.getInstance();

    @Test
    void contextLoads() {



    }

}
