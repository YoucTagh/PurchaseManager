package com.youctagh.purchasemanager.frontend.model.category;

import com.youctagh.purchasemanager.frontend.domain.Category;
import javafx.collections.ObservableList;

/**
 * @author YoucTagh
 */
public interface CategoryModel {
    ObservableList<Category> getCategoryList();

    boolean getOneCategory(Long id);

    boolean addCategory(ObservableList<Category> list, Category category);

    boolean deleteCategory(ObservableList<Category> list, Category category);

    boolean updateCategory(ObservableList<Category> list, Category oldItem, Category newItem);
}
