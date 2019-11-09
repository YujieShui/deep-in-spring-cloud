package com.shuiyujie.mapper;

import com.shuiyujie.vo.Product;

import java.util.List;

/**
 * @author shui
 * @create 2019-11-09
 **/
public interface ProductMapper {
    boolean create(Product product);
    public Product findById(Long id);
    public List<Product> findAll();
}

