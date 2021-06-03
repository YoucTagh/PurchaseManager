package com.youctagh.purchasemanager.frontend.controller;

import com.youctagh.purchasemanager.frontend.domain.Store;
import com.youctagh.purchasemanager.frontend.model.store.StoreModel;
import com.youctagh.purchasemanager.frontend.util.TableViewUtils;
import com.youctagh.purchasemanager.frontend.view.store.StoreView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import lombok.Getter;

import javax.swing.*;

/**
 * @author YoucTagh
 */
public class StoreController {
    @Getter
    private final GeneralController generalController;
    @Getter
    private final StoreView storeView;
    @Getter
    private final StoreModel storeModel;

    public StoreController(GeneralController generalController, StoreView storeView,
                           StoreModel storeModel) {
        this.generalController = generalController;
        this.storeView = storeView;
        this.storeModel = storeModel;
        initView();
    }

    private void initView() {
        initButtons();
        initDataTV();
    }

    private void initDataTV() {

        storeView.getDataTV().getColumns().addAll(
                TableViewUtils.getColumn(storeView.getDataTV(),
                        Store.class, String.class, "ID",
                        200, 3, "id"),
                TableViewUtils.getColumn(storeView.getDataTV(),
                        Store.class, String.class, "Name",
                        200, 3, "name"),
                TableViewUtils.getColumn(storeView.getDataTV(),
                        Store.class, String.class, "Address",
                        200, 3, "address")

        );

        storeView.getDataTV().setItems(storeModel.getStoreList());
    }

    private void initButtons() {
        storeView.getAddButton().addEventHandler(ActionEvent.ACTION, e -> {
            addNewModel();
        });

        storeView.getUpdateButton().addEventHandler(ActionEvent.ACTION, e -> {
            updateNewModel();
        });

        storeView.getDeleteButton().addEventHandler(ActionEvent.ACTION, e -> {
            deleteNewModel();
        });
    }

    private void deleteNewModel() {

        final Store store = storeView.getDataTV().getSelectionModel().getSelectedItem();
        final ObservableList<Store> items = storeView.getDataTV().getItems();
        if (storeModel.deleteStore(items, store)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }

    private void updateNewModel() {
        final String name = storeView.getNameTF().getText();
        final String address = storeView.getAddressTF().getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name Empty !!");
            return;
        }
        final Store oldItem = storeView.getDataTV().getSelectionModel().getSelectedItem();

        Store newItem = (Store) new Store()
                .setName(name)
                .setAddress(address)
                .setId(oldItem.getId());
        final ObservableList<Store> items = storeView.getDataTV().getItems();
        if (storeModel.updateStore(items, oldItem, newItem)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }

    private void addNewModel() {
        final String name = storeView.getNameTF().getText();
        final String address = storeView.getAddressTF().getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name Empty !!");
            return;
        }
        final Store store = new Store()
                .setName(name)
                .setAddress(address);
        final ObservableList<Store> items = storeView.getDataTV().getItems();
        if (storeModel.addStore(items, store)) {
            JOptionPane.showMessageDialog(null, "Successful !!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed !!");
        }
    }
}
