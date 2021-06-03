package com.youctagh.purchasemanager.frontend.view.general;

import com.youctagh.purchasemanager.frontend.domain.Product;
import com.youctagh.purchasemanager.frontend.view.category.CategoryView;
import com.youctagh.purchasemanager.frontend.view.product.ProductView;
import com.youctagh.purchasemanager.frontend.view.store.StoreView;
import com.youctagh.purchasemanager.frontend.view.ticket.TicketView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Getter;

/**
 * @author YoucTagh
 */
@Getter
public class GeneralView {

    @FXML
    private Button exportDateButton;
    @FXML
    private Button showStatButton;

    @FXML
    private CategoryView categoryView;

    @FXML
    private StoreView storeView;

    @FXML
    private ProductView productView;

    @FXML
    private TicketView ticketView;
}
