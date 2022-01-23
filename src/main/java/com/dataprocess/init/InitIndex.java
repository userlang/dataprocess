package com.dataprocess.init;

import com.dataprocess.es.index.DocIndex;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class InitIndex {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @PostConstruct
    void createIndex() throws Exception {
        //初始化es索引
        CreateIndexRequest request=new CreateIndexRequest(DocIndex.LOGININFO);
        if(!indexExists(DocIndex.LOGININFO)){
            CreateIndexResponse response= restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        }
        CreateIndexRequest request1=new CreateIndexRequest(DocIndex.USERTRACKING);
        if(!indexExists(DocIndex.USERTRACKING)){
            CreateIndexResponse response= restHighLevelClient.indices().create(request1, RequestOptions.DEFAULT);
        }
//        CreateIndexRequest request2=new CreateIndexRequest(DocIndex.UserAccount);//.source(generateBuilder1());
//        if(!indexExists(DocIndex.UserAccount)){
//
//            CreateIndexResponse response= restHighLevelClient.indices().create(request2, RequestOptions.DEFAULT);
//
//        }

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
    private XContentBuilder generateBuilder1() throws Exception{
        Map<String,Map<String,Object>> map=new HashMap<>();
        Map<String,Object> va=new HashMap<>();
        va.put("type","integer");
        map.put("id",va);

        XContentBuilder contentBuilder= XContentFactory.jsonBuilder();
        contentBuilder.startObject()
                .startObject("mappings")
                .field("properties",map)
                .endObject()
                .startObject("settings")
                .endObject()
                .endObject();
        return contentBuilder;

    }
    private XContentBuilder generateBuilder() throws Exception{
    XContentBuilder contentBuilder= XContentFactory.jsonBuilder();
     contentBuilder.startObject();
     {
        contentBuilder.startObject("properties");
         {
             contentBuilder.startObject("id");
             {
                 contentBuilder.field("type","integer");

             }
             contentBuilder.endObject();
         }
         {
             contentBuilder.startObject("name");
             {
                 contentBuilder.field("type","keyword");
             }
             contentBuilder.endObject();
         }
         {
             contentBuilder.startObject("age");
             {
                 contentBuilder.field("type","integer");
             }
             contentBuilder.endObject();
         }
         {
             contentBuilder.startObject("u_createtime");
             {
                 contentBuilder.field("type","date");

             }
             contentBuilder.endObject();
         }
         {
             contentBuilder.startObject("u_updatetime");
             {
                 contentBuilder.field("type","date");

             }
             contentBuilder.endObject();
         }
         {
             contentBuilder.startObject("userid");
             {
                 contentBuilder.field("type","integer");
             }
             contentBuilder.endObject();
         }
         {
             contentBuilder.startObject("account");
             {
                 contentBuilder.field("type","keyword");
             }
             contentBuilder.endObject();
         }
         {
             contentBuilder.startObject("a_createtime");
             {
                 contentBuilder.field("type","date");

             }
             contentBuilder.endObject();
         }
         {
             contentBuilder.startObject("a_updatetime");
             {
                 contentBuilder.field("type","date");

             }
             contentBuilder.endObject();
         }
         {
            contentBuilder.startObject("bank");
             {
                 contentBuilder.field("type","keyword");

             }
             contentBuilder.endObject();
         }
        contentBuilder.endObject();
     }
     contentBuilder.endObject();

    return contentBuilder;
    }
}
