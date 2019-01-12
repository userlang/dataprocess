package com.dataprocess.init;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 创建相关的es索引哦~
 */
@Component
@Order(value = 0)
public class InitIndex implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}
