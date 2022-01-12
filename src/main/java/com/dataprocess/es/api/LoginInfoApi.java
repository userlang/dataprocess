package com.dataprocess.es.api;

import com.dataprocess.es.dao.LoginInfoDao;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

@RestController
public class LoginInfoApi {

    @Resource
    RestHighLevelClient restHighLevelClient;

    @Resource
    LoginInfoDao loginInfoDao;

    @RequestMapping("search")
    public Iterable  search(){

       return loginInfoDao.findAll();
    }


}
