package com.youctagh.purchasemanager.backend.service;


import com.youctagh.purchasemanager.backend.controller.v1.dto.model.ProductDTO;
import com.youctagh.purchasemanager.backend.domain.Product;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface ProductService {
    Optional<ProductDTO> findById(Long id);
    HashSet<ProductDTO> findAll();
    Optional<ProductDTO> addProduct(Product product);
    Optional<ProductDTO> deleteProduct(Product product);
    Optional<ProductDTO> updateProduct(Product product);
}
