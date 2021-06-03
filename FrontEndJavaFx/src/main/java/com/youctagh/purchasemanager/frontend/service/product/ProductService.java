package com.youctagh.purchasemanager.frontend.service.product;

import com.youctagh.purchasemanager.frontend.domain.Product;
import com.youctagh.purchasemanager.frontend.request.ProductRequest;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface ProductService {
    Optional<Product> findById(Long id);

    HashSet<Product> findAll();

    Optional<Product> addProduct(ProductRequest request);

    Optional<Product> deleteProduct(Product request);

    Optional<Product> updateProduct(ProductRequest request);
}
