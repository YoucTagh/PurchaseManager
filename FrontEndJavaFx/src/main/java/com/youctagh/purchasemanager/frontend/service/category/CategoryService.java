package com.youctagh.purchasemanager.frontend.service.category;

import com.youctagh.purchasemanager.frontend.domain.Category;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface CategoryService {
    Optional<Category> findById(Long id);
    HashSet<Category> findAll();
    Optional<Category> addCategory(Category category);
    Optional<Category> deleteCategory(Category category);
    Optional<Category> updateCategory(Category category);
}
