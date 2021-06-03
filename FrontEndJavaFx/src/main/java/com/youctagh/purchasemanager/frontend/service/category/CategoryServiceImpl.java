package com.youctagh.purchasemanager.frontend.service.category;

import com.youctagh.purchasemanager.frontend.domain.Category;
import com.youctagh.purchasemanager.frontend.service.base.BaseServiceImpl;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {
    private final String relativeURL = "/category";

    @Override
    public Optional<Category> findById(Long id) {
        return super.findById(id, relativeURL, Category.class);
    }

    @Override
    public HashSet<Category> findAll() {
        return super.findAll(relativeURL, Category.class);
    }

    @Override
    public Optional<Category> addCategory(Category category) {
        return super.addObject(category, relativeURL, Category.class);
    }

    @Override
    public Optional<Category> deleteCategory(Category category) {
        return super.deleteObject(category, relativeURL, Category.class);
    }

    @Override
    public Optional<Category> updateCategory(Category category) {
        return super.updateObject(category, relativeURL, Category.class);
    }
}
