package com.youctagh.purchasemanager.frontend.controller;

import com.youctagh.purchasemanager.frontend.domain.Category;
import com.youctagh.purchasemanager.frontend.domain.Product;
import com.youctagh.purchasemanager.frontend.model.product.ProductModel;
import com.youctagh.purchasemanager.frontend.request.ProductRequest;
import com.youctagh.purchasemanager.frontend.util.TableViewUtils;
import com.youctagh.purchasemanager.frontend.view.product.ProductView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import lombok.Getter;

import javax.swing.*;

/**
 * @author YoucTagh
 */
public class ProductController {
    @Getter
    private final GeneralController generalController;
    @Getter
    private final ProductView productView;
    @Getter
    private final ProductModel productModel;

    public ProductController(GeneralController generalController, ProductView productView,
                             ProductModel productModel) {
        this.generalController = generalController;
        this.productView = productView;
        this.productModel = productModel;
        initView();
    }

    private void initView() {
        initButtons();
        initDataTV();
        initCB();
    }

    private void initCB() {

        productView.getCategoryCB().itemsProperty().bind(
                generalController.getCategoryController().getCategoryView().getDataTV().itemsProperty());
        Callback<ListView<Category>, ListCell<Category>> factory = (ListView<Category> param) -> {
            final ListCell<Category> cell = new ListCell<Category>() {

                @Override
                public void updateItem(Category item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText(item.getName());
                    } else {
                        setText(null);
                    }
                }
            };
            return cell;
        };

        productView.getCategoryCB().setCellFactory(factory);
        productView.getCategoryCB().setButtonCell(factory.call(null));
    }

    private void initDataTV() {

        productView.getDataTV().getColumns().addAll(
                TableViewUtils.getColumn(productView.getDataTV(),
                        Product.class, String.class, "ID",
                        200, 3, "id"),
                TableViewUtils.getColumn(productView.getDataTV(),
                        Product.class, String.class, "Name",
                        200, 3, "name"),
                TableViewUtils.getColumn(productView.getDataTV(),
                        Product.class, String.class, "Category",
                        200, 3, "categoryName")
        );

        productView.getDataTV().setItems(productModel.getProductList());
    }

    private void initButtons() {
        productView.getAddButton().addEventHandler(ActionEvent.ACTION, e -> {
            addNewModel();
        });

        productView.getUpdateButton().addEventHandler(ActionEvent.ACTION, e -> {
            updateNewModel();
        });

        productView.getDeleteButton().addEventHandler(ActionEvent.ACTION, e -> {
            deleteNewModel();
        });
    }

    private void deleteNewModel() {

        final Product product = productView.getDataTV().getSelectionModel().getSelectedItem();
        final ObservableList<Product> items = productView.getDataTV().getItems();
        if (productModel.deleteProduct(items, product)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }

    private void updateNewModel() {
        final String name = productView.getNameTF().getText();
        final Category category = productView.getCategoryCB().getSelectionModel().getSelectedItem();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name Empty !!");
            return;
        }else if(category == null){
            JOptionPane.showMessageDialog(null, "Select a Category !!");
            return;
        }
        final Product oldItem = productView.getDataTV().getSelectionModel().getSelectedItem();

        ProductRequest newItem = (ProductRequest) new ProductRequest()
                .setName(name)
                .setCategory_id(category.getId())
                .setId(oldItem.getId());
        final ObservableList<Product> items = productView.getDataTV().getItems();
        if (productModel.updateProduct(items, oldItem, newItem)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }

    private void addNewModel() {
        final String name = productView.getNameTF().getText();
        final Category category = productView.getCategoryCB().getSelectionModel().getSelectedItem();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name Empty !!");
            return;
        }else if(category == null){
            JOptionPane.showMessageDialog(null, "Select a Category !!");
            return;
        }
        final ProductRequest request = new ProductRequest()
                .setCategory_id(category.getId())
                .setName(name);
        final ObservableList<Product> items = productView.getDataTV().getItems();
        if (productModel.addProduct(items, request)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }
}
