package com.dataprocess.init;

import com.dataprocess.es.index.DocIndex;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class InitIndex {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @PostConstruct
    void createIndex() throws IOException {
        //初始化es索引
        CreateIndexRequest request=new CreateIndexRequest(DocIndex.LOGININFO);
        if(!indexExists(DocIndex.LOGININFO)){
            CreateIndexResponse response= restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        }
        CreateIndexRequest request1=new CreateIndexRequest(DocIndex.USERTRACKING);
        if(!indexExists(DocIndex.USERTRACKING)){
            CreateIndexResponse response= restHighLevelClient.indices().create(request1, RequestOptions.DEFAULT);
        }


    }

    public  boolean indexExists(String index) throws IOException {
             GetIndexRequest re = new GetIndexRequest(index);
           return restHighLevelClient.indices().exists(re,RequestOptions.DEFAULT);
    }
    public void deleteDocument(String index) throws IOException {
        //创建删除请求，指定索引和删除的id
        DeleteRequest request = new DeleteRequest(index);

        DeleteResponse delete = restHighLevelClient.delete(request, RequestOptions.DEFAULT);

        System.out.println(delete.status());
        System.out.println(delete.toString());
    }
}
