package com.example.elasticsearchdemo.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentDao extends ElasticsearchRepository<com.example.elasticsearchdemo.document.UserDocument,String> {
}
