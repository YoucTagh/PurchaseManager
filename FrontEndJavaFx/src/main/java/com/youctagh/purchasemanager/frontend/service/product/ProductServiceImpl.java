package com.youctagh.purchasemanager.frontend.service.product;

import com.youctagh.purchasemanager.frontend.domain.Product;
import com.youctagh.purchasemanager.frontend.request.ProductRequest;
import com.youctagh.purchasemanager.frontend.service.base.BaseServiceImpl;
import com.youctagh.purchasemanager.frontend.service.product.ProductService;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {
    private final String relativeURL = "/product";

    @Override
    public Optional<Product> findById(Long id) {
        return super.findById(id, relativeURL, Product.class);
    }

    @Override
    public HashSet<Product> findAll() {
        return super.findAll(relativeURL, Product.class);
    }

    @Override
    public Optional<Product> addProduct(ProductRequest request) {
        return super.addObject(request, relativeURL, Product.class);
    }

    @Override
    public Optional<Product> deleteProduct(Product product) {
        return super.deleteObject(product, relativeURL, Product.class);
    }

    @Override
    public Optional<Product> updateProduct(ProductRequest request) {
        return super.updateObject(request, relativeURL, Product.class);
    }
}
