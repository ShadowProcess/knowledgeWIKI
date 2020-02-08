package com.example.elasticsearchdemo.document;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * String indexName(); //索引库名，个人建议以项目名zhidao称命名
 * String type() default ""; //类型，个人建议以实体类名称命名
 * short shards() default 5; //默认分区数版
 * short replicas() default 1; //每个分区默认的备份数
 * String refreshInterval() default "1s"; //刷新间隔
 * String indexStoreType() default "fs"; //索引文件存储权类型
 */

@Document(indexName = "fuleya")
public class UserDocument {

    @Field(type = FieldType.Auto)
    private String id;

    @Field(type = FieldType.Auto)
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
