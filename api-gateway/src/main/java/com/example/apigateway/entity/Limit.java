package com.example.apigateway.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Limit {
    private long limit;//数据刷新时间
    private long frequency;//频率
    private long timstamp;
}
