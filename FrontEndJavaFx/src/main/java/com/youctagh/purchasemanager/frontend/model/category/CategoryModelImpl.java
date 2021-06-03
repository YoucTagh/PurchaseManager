package com.youctagh.purchasemanager.frontend.model.category;

import com.youctagh.purchasemanager.frontend.domain.Category;
import com.youctagh.purchasemanager.frontend.service.category.CategoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

/**
 * @author YoucTagh
 */
public class CategoryModelImpl implements CategoryModel {

    private final CategoryService categoryService;

    public CategoryModelImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    public ObservableList<Category> getCategoryList() {
        return FXCollections.observableArrayList(categoryService.findAll());
    }

    @Override
    public boolean getOneCategory(Long id) {
        final Optional<Category> optional = categoryService.findById(id);
        if (optional.isPresent()) {
            System.out.println(optional.get().getName());
            return true;
        } else {
            System.out.println("Empty");
            return false;
        }

    }

    @Override
    public boolean addCategory(ObservableList<Category> list, Category category) {
        final Optional<Category> answer = categoryService.addCategory(category);
        if (answer.isPresent()) {
            list.add(answer.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCategory(ObservableList<Category> list, Category category) {
        final Optional<Category> answer = categoryService.deleteCategory(category);
        if (answer.isPresent()) {
            list.remove(category);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateCategory(ObservableList<Category> list, Category oldItem, Category newItem) {
        final Optional<Category> answer = categoryService.updateCategory(newItem);
        if (answer.isPresent()) {
            list.remove(oldItem);
            list.add(newItem);
            return true;
        } else {
            return false;
        }
    }
}
