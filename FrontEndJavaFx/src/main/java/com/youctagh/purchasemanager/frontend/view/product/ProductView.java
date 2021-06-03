package com.youctagh.purchasemanager.frontend.view.product;

import com.youctagh.purchasemanager.frontend.domain.Category;
import com.youctagh.purchasemanager.frontend.domain.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.io.IOException;

/**
 * @author YoucTagh
 */
@Getter
public class ProductView extends VBox {

    @FXML
    private TextField nameTF;

    @FXML
    private ComboBox<Category> categoryCB;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Product> dataTV;

    @FXML
    private Button addButton;

    public ProductView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/youctagh/purchasemanager/frontend/view/product/ProductView.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
