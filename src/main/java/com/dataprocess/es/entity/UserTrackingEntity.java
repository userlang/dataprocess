package com.dataprocess.es.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

// es 创建索引 put http://localhost:9200/<index>/<type>/[<id>]
@Document(indexName = "langking")
public class UserTrackingEntity {

    @Id
    private String id;
    private String referer;
    private String ip;
    private String requestUrl;
    private String userId;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    private Date createTime;
    private String reqMillisecond;
    private String param;
    private String result;
    private int executeFlag;
    private String errorDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReqMillisecond() {
        return reqMillisecond;
    }

    public void setReqMillisecond(String reqMillisecond) {
        this.reqMillisecond = reqMillisecond;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getExecuteFlag() {
        return executeFlag;
    }

    public void setExecuteFlag(int executeFlag) {
        this.executeFlag = executeFlag;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
