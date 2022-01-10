package com.dataprocess.es.dao;

import com.dataprocess.es.entity.UserTrackingEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserTrackingDao extends ElasticsearchRepository<UserTrackingEntity, String> {



}
