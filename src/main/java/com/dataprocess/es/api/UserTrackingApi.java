package com.dataprocess.es.api;

import com.dataprocess.es.dao.UserTrackingDao;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

//@RestController
public class UserTrackingApi {


    @Resource
    UserTrackingDao kangkingDao;
    @Resource
    RestHighLevelClient restHighLevelClient;

    @RequestMapping("search")
    public  SearchResponse search(@RequestParam("merchantName") String merchantName){


        /**
         *
         *          *  must
         * 			所有的语句都 必须（must） 匹配，与 AND 等价。
         * 			must_not
         * 			所有的语句都 不能（must not） 匹配，与 NOT 等价。
         * 			should
         * 			至少有一个语句要匹配，与 OR 等价。
         * 			trem
         * 			精确查找 与= 号等价。
         * 			match
         * 			模糊匹配 与like 等价。
         *
         *  */

            //1创建搜索请求对象
        String indexName="clue";
        SearchRequest request=new SearchRequest(indexName);
//        request.searchType("merchant");
        SearchSourceBuilder ssb=new SearchSourceBuilder();
        ssb.fetchField("merchant_name");
      //  QueryBuilder qb=QueryBuilders.multiMatchQuery("merchant_name",merchantName);
        QueryBuilder qb = QueryBuilders.termQuery("merchant_name.keyword",merchantName);

        ssb.query(qb);
        request.source(ssb);
        StringBuilder result=new StringBuilder();
        SearchResponse response=null;
        try{
             response= restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHits  hits= response.getHits();
            TotalHits totalHits=hits.getTotalHits();
            long total=totalHits.value;
            result.append("total=");
            result.append(total);
            result.append(hits.getHits());

        }catch (Exception e){
            e.printStackTrace();
        }




    return response;
    }

    //版本1 127.0.0.1:9991/queryCreateTime?startTime=20221101&endTime=20221111
    //版本2 http://127.0.0.1:9991/queryCreateTime?startTime=2021-12-26&endTime=2021-12-26&pageNum=1&pageSize=20
   //版本3 http://127.0.0.1:9991/queryCreateTime?startTime=2022-01-02&endTime=2022-01-02&pageNum=1&pageSize=20&ip=172.20.10.2&param=2022
    @RequestMapping("/queryCreateTime")
    public SearchResponse queryLangkingByCreateTime(
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime")String endTime,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("ip") String ip,
            @RequestParam("param") String param
            ){
        //1创建查询请求类
        SearchRequest request=new SearchRequest("langking");
        //2创建查询返回列
        SearchSourceBuilder ssb=new SearchSourceBuilder();
        ssb.fetchField("createTime");
        ssb.fetchField("ip");
        ssb.fetchField("param");

        //3.1查询条件日期 大于小于
        QueryBuilder qb=QueryBuilders.
                rangeQuery("createTime").
                format("yyyy-MM-dd")
                .gte(startTime).lte(endTime);
       String[] ips=null;
        QueryBuilder qb1=null;
        if(null!=ip){
            ips=ip.split("-");
            //3.2查询条件2 精准查询
                 qb1=QueryBuilders.termsQuery("ip",ips);

            // qb1=QueryBuilders.termsQuery("ip",ips);



        }

        //3.3模糊查询
        QueryBuilder qb2=QueryBuilders.wildcardQuery("param","*"+param+"*");

        //  or

        QueryBuilder qb3=QueryBuilders.boolQuery()
                .should(QueryBuilders.spanTermQuery("userId","17700000000")).
                should(QueryBuilders.spanTermQuery("userId","17700000001")).
        should(QueryBuilders.spanTermQuery("userId","langlang"));
        QueryBuilder and=QueryBuilders.boolQuery().must(qb).must(qb1).must(qb3).must(qb2);
        ssb.query(and);
        Pageable page= PageRequest.of(pageNum,pageSize);
        ssb.from((pageNum-1)*pageSize).size(pageSize);
//        ssb.sort("_id", SortOrder.DESC);
        request.source(ssb);
        SearchResponse response=null;
        try{
            response= restHighLevelClient.search(request,RequestOptions.DEFAULT);

        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }
}
