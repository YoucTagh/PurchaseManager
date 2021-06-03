package com.youctagh.purchasemanager.backend.service;


import com.youctagh.purchasemanager.backend.controller.v1.dto.mapper.ProductMapper;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.ProductDTO;
import com.youctagh.purchasemanager.backend.domain.Category;
import com.youctagh.purchasemanager.backend.domain.Product;
import com.youctagh.purchasemanager.backend.exception.EntityType;
import com.youctagh.purchasemanager.backend.exception.ExceptionController;
import com.youctagh.purchasemanager.backend.exception.ExceptionType;
import com.youctagh.purchasemanager.backend.repository.CategoryRepository;
import com.youctagh.purchasemanager.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author YoucTagh
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent())
            return Optional.ofNullable(ProductMapper.toProductDTO(productOptional.get()));

        throw ExceptionController.throwException(EntityType.PRODUCT,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id));
    }

    @Override
    public HashSet<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toProductDTO)
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Optional<ProductDTO> addProduct(Product product) {
        Optional<Product> productOptional = productRepository.findByName(product.getName());
        if (productOptional.isEmpty()) {
            Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory().getId());
            if (categoryOptional.isPresent()) {
                product.setCategory(categoryOptional.get());
                return Optional.ofNullable(ProductMapper.toProductDTO(productRepository.save(product)));
            }

            throw ExceptionController.throwException(EntityType.CATEGORY,
                    ExceptionType.ENTITY_NOT_FOUND, String.valueOf(product.getCategory().getId()));
        }
        throw ExceptionController.throwException(EntityType.PRODUCT,
                ExceptionType.DUPLICATE_ENTITY, String.valueOf(productOptional.get().getId()));

    }

    @Transactional
    @Override
    public Optional<ProductDTO> deleteProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());

        if (productOptional.isPresent()) {
            productRepository.deleteById(productOptional.get().getId());
            return Optional.ofNullable(ProductMapper.toProductDTO(productOptional.get()));
        }
        throw ExceptionController.throwException(EntityType.PRODUCT,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(product.getId()));
    }

    @Override
    public Optional<ProductDTO> updateProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            Optional<Product> productOptionalDuplication = productRepository.findByName(product.getName());
            if (productOptionalDuplication.isEmpty()) {
                Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory().getId());
                if (categoryOptional.isPresent()) {
                    product.setCategory(categoryOptional.get());
                    return Optional.ofNullable(ProductMapper.toProductDTO(productRepository.save(product)));
                }
                throw ExceptionController.throwException(EntityType.CATEGORY,
                        ExceptionType.ENTITY_NOT_FOUND, String.valueOf(product.getCategory().getId()));
            }
            throw ExceptionController.throwException(EntityType.PRODUCT,
                    ExceptionType.DUPLICATE_ENTITY, String.valueOf(productOptional.get().getId()));
        }
        throw ExceptionController.throwException(EntityType.PRODUCT,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(product.getId()));
    }
}
