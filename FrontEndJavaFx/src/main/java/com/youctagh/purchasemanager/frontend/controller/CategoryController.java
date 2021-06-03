package com.youctagh.purchasemanager.frontend.controller;

import com.youctagh.purchasemanager.frontend.domain.Category;
import com.youctagh.purchasemanager.frontend.model.category.CategoryModel;
import com.youctagh.purchasemanager.frontend.util.TableViewUtils;
import com.youctagh.purchasemanager.frontend.view.category.CategoryView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import lombok.Getter;

import javax.swing.*;

/**
 * @author YoucTagh
 */
public class CategoryController {
    @Getter
    private final GeneralController generalController;
    @Getter
    private final CategoryView categoryView;
    @Getter
    private final CategoryModel categoryModel;

    public CategoryController(GeneralController generalController, CategoryView categoryView,
                              CategoryModel categoryModel) {
        this.generalController = generalController;
        this.categoryView = categoryView;
        this.categoryModel = categoryModel;
        initView();
    }

    private void initView() {
        initButtons();
        initDataTV();
    }

    private void initDataTV() {

        categoryView.getDataTV().getColumns().addAll(
                TableViewUtils.getColumn(categoryView.getDataTV(),
                        Category.class, String.class, "ID",
                        200, 2, "id"),
                TableViewUtils.getColumn(categoryView.getDataTV(),
                        Category.class, String.class, "Name",
                        200, 2, "name")
        );

        categoryView.getDataTV().setItems(categoryModel.getCategoryList());
    }

    private void initButtons() {
        categoryView.getAddButton().addEventHandler(ActionEvent.ACTION, e -> {
            addNewModel();
        });

        categoryView.getUpdateButton().addEventHandler(ActionEvent.ACTION, e -> {
            updateNewModel();
        });

        categoryView.getDeleteButton().addEventHandler(ActionEvent.ACTION, e -> {
            deleteNewModel();
        });
    }

    private void deleteNewModel() {

        final Category category = categoryView.getDataTV().getSelectionModel().getSelectedItem();
        final ObservableList<Category> items = categoryView.getDataTV().getItems();
        if (categoryModel.deleteCategory(items, category)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }

    private void updateNewModel() {
        final String name = categoryView.getNameTF().getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name Empty !!");
            return;
        }
        final Category oldItem = categoryView.getDataTV().getSelectionModel().getSelectedItem();

        Category newItem = (Category) new Category().setName(name).setId(oldItem.getId());
        final ObservableList<Category> items = categoryView.getDataTV().getItems();
        if (categoryModel.updateCategory(items, oldItem,newItem)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }

    private void addNewModel() {
        final String name = categoryView.getNameTF().getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name Empty !!");
            return;
        }
        final Category category = new Category().setName(name);
        final ObservableList<Category> items = categoryView.getDataTV().getItems();
        if (categoryModel.addCategory(items, category)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }
}
