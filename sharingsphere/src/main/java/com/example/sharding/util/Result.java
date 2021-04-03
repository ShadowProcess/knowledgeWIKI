package com.example.sharding.util;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class Result<T> {
    private int success;
    private T data;
}
