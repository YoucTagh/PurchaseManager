package com.youctagh.purchasemanager.frontend.mapper;


import com.youctagh.purchasemanager.frontend.domain.Category;
import com.youctagh.purchasemanager.frontend.domain.Product;
import com.youctagh.purchasemanager.frontend.request.ProductRequest;

/**
 * @author YoucTagh
 */

public class ProductMapper {

    public static ProductRequest toProductRequest(Product product) {
        return (ProductRequest) new ProductRequest()
                .setName(product.getName())
                .setCategory_id(product.getCategory().getId())
                .setId(product.getId());
    }

    public static Product requestToProduct(ProductRequest request) {
        return (Product) new Product()
                .setName(request.getName())
                .setCategory((Category) new Category()
                        .setId(request.getCategory_id()))
                .setId(request.getId());
    }
}
