package com.youctagh.purchasemanager.frontend.model.product;

import com.youctagh.purchasemanager.frontend.domain.Category;
import com.youctagh.purchasemanager.frontend.domain.Product;
import com.youctagh.purchasemanager.frontend.request.ProductRequest;
import javafx.collections.ObservableList;

/**
 * @author YoucTagh
 */
public interface ProductModel {
    ObservableList<Product> getProductList();

    boolean getOneProduct(Long id);

    boolean addProduct(ObservableList<Product> list, ProductRequest request);

    boolean deleteProduct(ObservableList<Product> list, Product product);

    boolean updateProduct(ObservableList<Product> list, Product oldItem, ProductRequest request);
}
