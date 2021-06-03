package com.youctagh.purchasemanager.backend.service;

import com.youctagh.purchasemanager.backend.controller.v1.dto.model.CategoryDTO;
import com.youctagh.purchasemanager.backend.domain.Category;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface CategoryService {
    Optional<CategoryDTO> findById(Long id);
    HashSet<CategoryDTO> findAll();
    Optional<CategoryDTO> addCategory(Category category);
    Optional<CategoryDTO> deleteCategory(Category category);
    Optional<CategoryDTO> updateCategory(Category category);
}
