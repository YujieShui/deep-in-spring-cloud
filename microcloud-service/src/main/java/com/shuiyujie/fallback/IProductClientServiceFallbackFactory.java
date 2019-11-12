package com.shuiyujie.fallback;

import com.shuiyujie.service.IProductClientService;
import com.shuiyujie.vo.Product;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用于 IProductClientService 降级处理
 *
 * @author shui
 * @create 2019-11-11
 **/
@Component
public class IProductClientServiceFallbackFactory implements FallbackFactory<IProductClientService> {
    @Override
    public IProductClientService create(Throwable throwable) {
        return  new IProductClientService() {
            @Override
            public Product getProduct(long id) {
                Product product = new Product();
                product.setProductId(999999L);
                product.setProductName("feign-hystrixName");
                product.setProductDesc("feign-hystrixDesc");
                return  product;
            }

            @Override
            public List<Product> listProduct() {
                return null;
            }

            @Override
            public boolean addPorduct(Product product) {
                return false;
            }
        };

    }
}
