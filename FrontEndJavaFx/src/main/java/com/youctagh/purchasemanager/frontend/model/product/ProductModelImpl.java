package com.youctagh.purchasemanager.frontend.model.product;

import com.youctagh.purchasemanager.frontend.domain.Product;
import com.youctagh.purchasemanager.frontend.request.ProductRequest;
import com.youctagh.purchasemanager.frontend.service.category.CategoryService;
import com.youctagh.purchasemanager.frontend.service.product.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author YoucTagh
 */
public class ProductModelImpl implements ProductModel {

    private final ProductService productService;

    public ProductModelImpl(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
    }


    public ObservableList<Product> getProductList() {
        return FXCollections.observableArrayList(
                productService.findAll()
                        .stream()
                        .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                        .collect(Collectors.toList()));
    }


    @Override
    public boolean getOneProduct(Long id) {
        final Optional<Product> optional = productService.findById(id);
        if (optional.isPresent()) {
            System.out.println(optional.get().getName());
            return true;
        } else {
            System.out.println("Empty");
            return false;
        }

    }

    @Override
    public boolean addProduct(ObservableList<Product> list, ProductRequest request) {
        final Optional<Product> answer = productService.addProduct(request);
        if (answer.isPresent()) {
            list.add(answer.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteProduct(ObservableList<Product> list, Product product) {
        final Optional<Product> answer = productService.deleteProduct(product);
        if (answer.isPresent()) {
            list.remove(product);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateProduct(ObservableList<Product> list, Product oldItem, ProductRequest request) {
        final Optional<Product> answer = productService.updateProduct(request);
        if (answer.isPresent()) {
            list.remove(oldItem);
            list.add(answer.get());
            return true;
        } else {
            return false;
        }
    }
}
