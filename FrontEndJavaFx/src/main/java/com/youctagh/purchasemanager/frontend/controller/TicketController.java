package com.youctagh.purchasemanager.frontend.controller;

import com.youctagh.purchasemanager.frontend.domain.Item;
import com.youctagh.purchasemanager.frontend.domain.Product;
import com.youctagh.purchasemanager.frontend.domain.Store;
import com.youctagh.purchasemanager.frontend.domain.Ticket;
import com.youctagh.purchasemanager.frontend.model.ticket.TicketModel;
import com.youctagh.purchasemanager.frontend.request.ItemRequest;
import com.youctagh.purchasemanager.frontend.request.TicketRequest;
import com.youctagh.purchasemanager.frontend.util.TableViewUtils;
import com.youctagh.purchasemanager.frontend.view.ticket.TicketView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.Callback;
import lombok.Getter;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author YoucTagh
 */
public class TicketController {
    @Getter
    private final GeneralController generalController;
    @Getter
    private final TicketView ticketView;
    @Getter
    private final TicketModel ticketModel;

    public TicketController(GeneralController generalController, TicketView ticketView,
                            TicketModel ticketModel) {
        this.generalController = generalController;
        this.ticketView = ticketView;
        this.ticketModel = ticketModel;
        initView();
    }

    private void initView() {
        initItemTV();
        initButtons();
        initDataTV();
        initChangeTV();
        initCB();
        initSpinner();
    }

    private void initSpinner() {
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 0);
        ticketView.getItemPriceSpinner().setValueFactory(valueFactory);
        ticketView.getItemPriceSpinner().setPromptText("item price");
    }

    private void initChangeTV() {
        ticketView.getDataTV().getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newVlue) -> {
                    setTicketInfo(newVlue);
                });
    }

    private void setTicketInfo(Ticket ticket) {
        if (ticket == null) {
            ticketView.getDateDP().setValue(null);
            ticketView.getStoreCB().getSelectionModel().clearSelection();
            ticketView.getItemTV().getItems().clear();
            ticketView.getItemCommentTA().setText("");
            ticketView.getItemPriceSpinner().getValueFactory().setValue(0D);
        } else {
            ticketView.getDateDP().setValue(LocalDate.ofInstant(ticket.getDate().toInstant(), ZoneId.systemDefault()));
            ticketView.getStoreCB().getSelectionModel().select(ticket.getStore());
            ticketView.getItemTV().getItems().clear();
            ticketView.getItemTV().setItems(FXCollections.observableList(new ArrayList<>(ticket.getItems())));
        }
    }

    private void initCB() {
        initStoreCB();
        initProductCB();
    }

    private void initStoreCB() {
        ticketView.getStoreCB().itemsProperty().bind(
                generalController.getStoreController().getStoreView().getDataTV().itemsProperty());
        Callback<ListView<Store>, ListCell<Store>> factory = (ListView<Store> param) -> {
            final ListCell<Store> cell = new ListCell<Store>() {

                @Override
                public void updateItem(Store item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText(item.getName());
                    } else {
                        setText("Select Store");
                    }
                }
            };
            return cell;
        };

        ticketView.getStoreCB().setCellFactory(factory);
        ticketView.getStoreCB().setButtonCell(factory.call(null));
    }

    private void initProductCB() {
        ticketView.getProductCB().itemsProperty().bind(
                generalController.getProductController().getProductView().getDataTV().itemsProperty());
        Callback<ListView<Product>, ListCell<Product>> factory = (ListView<Product> param) -> {
            final ListCell<Product> cell = new ListCell<Product>() {

                @Override
                public void updateItem(Product item, boolean empty) {
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

        ticketView.getProductCB().setCellFactory(factory);
        ticketView.getProductCB().setButtonCell(factory.call(null));
    }

    private void initDataTV() {

        ticketView.getDataTV().getColumns().addAll(
                TableViewUtils.getColumn(ticketView.getDataTV(),
                        Ticket.class, String.class, "ID",
                        150, 4, "id"),
                TableViewUtils.getColumn(ticketView.getDataTV(),
                        Ticket.class, String.class, "Store",
                        150, 4, "storeName"),
                TableViewUtils.getColumn(ticketView.getDataTV(),
                        Ticket.class, String.class, "Date",
                        150, 4, "date"),
                TableViewUtils.getColumn(ticketView.getDataTV(),
                        Ticket.class, Integer.class, "Item Number",
                        150, 4, "itemNumber")
        );

        ticketView.getDataTV().setItems(ticketModel.getTicketList());

    }


    private void initItemTV() {

        ticketView.getItemTV().getColumns().addAll(
                TableViewUtils.getColumn(ticketView.getItemTV(),
                        Item.class, String.class, "Product",
                        30, 3, "productName"),
                TableViewUtils.getColumn(ticketView.getItemTV(),
                        Item.class, String.class, "Price",
                        30, 3, "price"),
                TableViewUtils.getColumn(ticketView.getItemTV(),
                        Item.class, String.class, "Comment",
                        30, 3, "comment")
        );

        ticketView.getItemTV().getItems().addListener((ListChangeListener<Item>) change -> {
            final ObservableList<? extends Item> list = change.getList();
            updateTotalPrice(list);
        });
        ticketView.getItemTV().itemsProperty().addListener((observableValue, oldItem, newItem) -> {
            updateTotalPrice(newItem);
            newItem.addListener((ListChangeListener<Item>) change -> {
                final ObservableList<? extends Item> list = change.getList();
                updateTotalPrice(list);
            });

        });

    }

    private void updateTotalPrice(ObservableList<? extends Item> list) {
        if (list.isEmpty()) {
            ticketView.getTotalLabel().setText("Total: -");
        } else {
            Double sum = list.stream().map(Item::getPrice).reduce(0D, Double::sum);
            ticketView.getTotalLabel().setText("Total: " + sum);
        }
    }

    private void initButtons() {
        ticketView.getAddButton().addEventHandler(ActionEvent.ACTION, e -> {
            addNewModel();
        });

        ticketView.getUpdateButton().addEventHandler(ActionEvent.ACTION, e -> {
            updateNewModel();
        });

        ticketView.getDeleteButton().addEventHandler(ActionEvent.ACTION, e -> {
            deleteNewModel();
        });

        ticketView.getAddItemButton().addEventHandler(ActionEvent.ACTION, e -> {
            addNewItem();
        });

        ticketView.getDeleteItemButton().addEventHandler(ActionEvent.ACTION, e -> {
            deleteItem();
        });
    }

    private void deleteNewModel() {

        final Ticket ticket = ticketView.getDataTV().getSelectionModel().getSelectedItem();
        final ObservableList<Ticket> items = ticketView.getDataTV().getItems();
        if (ticketModel.deleteTicket(items, ticket)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }

    private void updateNewModel() {
        final Date date = java.sql.Date.valueOf(ticketView.getDateDP().getValue());
        final Store store = ticketView.getStoreCB().getSelectionModel().getSelectedItem();
        final String comment = ticketView.getItemCommentTA().getText();
        if (store == null) {
            JOptionPane.showMessageDialog(null, "Name Empty !!");
            return;
        }
        final Ticket oldItem = ticketView.getDataTV().getSelectionModel().getSelectedItem();

        TicketRequest newItem = (TicketRequest) new TicketRequest()
                .setDate(date)
                .setStore_id(store.getId())

                .setItems(ticketView.getItemTV().getItems().stream().map(item ->
                        new ItemRequest()
                                .setComment(item.getComment())
                                .setPrice(item.getPrice())
                                .setProduct_id(item.getProduct().getId())).collect(Collectors.toSet()))
                .setId(oldItem.getId());
        final ObservableList<Ticket> items = ticketView.getDataTV().getItems();
        if (ticketModel.updateTicket(items, oldItem, newItem)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }

    private void addNewModel() {
        final Date date = java.sql.Date.valueOf(ticketView.getDateDP().getValue());
        final Store store = ticketView.getStoreCB().getSelectionModel().getSelectedItem();
        if (store == null) {
            JOptionPane.showMessageDialog(null, "Date Empty !!");
            return;
        }
        final TicketRequest request = new TicketRequest()
                .setDate(date)
                .setStore_id(store.getId())
                .setItems(ticketView.getItemTV().getItems().stream().map(item ->
                        new ItemRequest()
                                .setPrice(item.getPrice())
                                .setComment(item.getComment())
                                .setProduct_id(item.getProduct().getId())).collect(Collectors.toSet()));
        final ObservableList<Ticket> items = ticketView.getDataTV().getItems();
        if (ticketModel.addTicket(items, request)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }

    private void addNewItem() {
        final Double price = ticketView.getItemPriceSpinner().getValue();
        final Product product = ticketView.getProductCB().getSelectionModel().getSelectedItem();
        final String comment = ticketView.getItemCommentTA().getText();
        if (price == 0) {
            JOptionPane.showMessageDialog(null, "Name Empty !!");
            return;
        }
        final Item item = new Item()
                .setPrice(price)
                .setProduct(product)
                .setComment(comment);
        ticketView.getItemTV().getItems().add(item);
    }

    private void deleteItem() {
        final Item selectedItem = ticketView.getItemTV().getSelectionModel().getSelectedItem();
        if (selectedItem != null)
            ticketView.getItemTV().getItems().remove(selectedItem);
        else
            JOptionPane.showMessageDialog(null, "Select Item !!");

    }
}
