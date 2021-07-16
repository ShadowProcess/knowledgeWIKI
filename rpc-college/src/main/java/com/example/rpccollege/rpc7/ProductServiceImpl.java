package com.example.rpccollege.rpc7;


import com.example.rpccollege.common.IProductService;
import com.example.rpccollege.common.Product;

/**
 * 放在服务端的一个方法
 */
public class ProductServiceImpl implements IProductService {
    @Override
    public Product findProductById(Integer id) {
        return new Product(id, "Apple");
    }
}
