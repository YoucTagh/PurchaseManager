package com.youctagh.purchasemanager.backend.controller.v1.dto.mapper;

import com.youctagh.purchasemanager.backend.controller.v1.dto.model.ProductDTO;
import com.youctagh.purchasemanager.backend.controller.v1.request.ProductRequest;
import com.youctagh.purchasemanager.backend.domain.Category;
import com.youctagh.purchasemanager.backend.domain.Product;
import org.springframework.stereotype.Component;

/**
 * @author YoucTagh
 */
@Component
public class ProductMapper {

    public static ProductDTO toProductDTO(Product product) {
        return (ProductDTO) new ProductDTO()
                .setName(product.getName())
                .setCategory(product.getCategory() == null ? null : CategoryMapper.toCategoryDTO(product.getCategory()))
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
