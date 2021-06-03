package com.youctagh.purchasemanager.backend.service;


import com.youctagh.purchasemanager.backend.controller.v1.dto.mapper.CategoryMapper;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.CategoryDTO;
import com.youctagh.purchasemanager.backend.domain.Category;
import com.youctagh.purchasemanager.backend.exception.EntityType;
import com.youctagh.purchasemanager.backend.exception.ExceptionController;
import com.youctagh.purchasemanager.backend.exception.ExceptionType;
import com.youctagh.purchasemanager.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author YoucTagh
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<CategoryDTO> findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent())
            return Optional.ofNullable(CategoryMapper.toCategoryDTO(categoryOptional.get()));

        throw ExceptionController.throwException(EntityType.CATEGORY,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id));
    }

    @Override
    public HashSet<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toCategoryDTO)
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Optional<CategoryDTO> addCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
        if (categoryOptional.isEmpty()) {
            return Optional.ofNullable(CategoryMapper.toCategoryDTO(categoryRepository.save(category)));
        }
        throw ExceptionController.throwException(EntityType.CATEGORY,
                ExceptionType.DUPLICATE_ENTITY, String.valueOf(categoryOptional.get().getId()));

    }

    @Transactional
    @Override
    public Optional<CategoryDTO> deleteCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(category.getId());

        if (categoryOptional.isPresent()) {
            categoryRepository.deleteById(categoryOptional.get().getId());
            return Optional.ofNullable(CategoryMapper.toCategoryDTO(categoryOptional.get()));
        }
        throw ExceptionController.throwException(EntityType.CATEGORY,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(category.getId()));
    }

    @Override
    public Optional<CategoryDTO> updateCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(category.getId());
        if (categoryOptional.isPresent()) {
            Optional<Category> categoryOptionalDuplication = categoryRepository.findByName(category.getName());
            if (categoryOptionalDuplication.isEmpty()) {
                return Optional.ofNullable(CategoryMapper.toCategoryDTO(categoryRepository.save(category)));
            }
            throw ExceptionController.throwException(EntityType.CATEGORY,
                    ExceptionType.DUPLICATE_ENTITY, String.valueOf(categoryOptional.get().getName()));
        }
        throw ExceptionController.throwException(EntityType.CATEGORY,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(category.getId()));
    }
}
