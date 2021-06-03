package com.youctagh.purchasemanager.frontend.view.category;

import com.youctagh.purchasemanager.frontend.domain.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.io.IOException;

/**
 * @author YoucTagh
 */
@Getter
public class CategoryView extends VBox {

    @FXML
    private TextField nameTF;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Category> dataTV;

    @FXML
    private Button addButton;

    public CategoryView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/youctagh/purchasemanager/frontend/view/category/CategoryView.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
