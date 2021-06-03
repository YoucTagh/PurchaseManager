package com.youctagh.purchasemanager.frontend.view.ticket;

import com.youctagh.purchasemanager.frontend.domain.Item;
import com.youctagh.purchasemanager.frontend.domain.Product;
import com.youctagh.purchasemanager.frontend.domain.Store;
import com.youctagh.purchasemanager.frontend.domain.Ticket;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.io.IOException;

/**
 * @author YoucTagh
 */
@Getter
public class TicketView extends VBox {

    @FXML
    private ComboBox<Store> storeCB;

    @FXML
    private ComboBox<Product> productCB;

    @FXML
    private DatePicker dateDP;

    @FXML
    private Spinner<Double> itemPriceSpinner;

    @FXML
    private Button addItemButton;

    @FXML
    private Button deleteItemButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Ticket> dataTV;

    @FXML
    private TableView<Item> itemTV;

    @FXML
    private Button addButton;

    @FXML
    private TextArea itemCommentTA;

    public TicketView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/youctagh/purchasemanager/frontend/view/ticket/TicketView.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
