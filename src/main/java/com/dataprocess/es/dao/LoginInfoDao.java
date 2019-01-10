package com.dataprocess.es.dao;

import com.dataprocess.es.entity.LoginInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface LoginInfoDao extends ElasticsearchRepository<LoginInfo, String> {


}
