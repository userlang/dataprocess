package com.dataprocess.es.dao;

import com.dataprocess.es.entity.LoginInfoEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface LoginInfoDao extends ElasticsearchRepository<LoginInfoEntity, Integer> {


}
