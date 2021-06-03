package com.youctagh.purchasemanager.frontend.model.store;

import com.youctagh.purchasemanager.frontend.domain.Store;
import com.youctagh.purchasemanager.frontend.model.store.StoreModel;
import com.youctagh.purchasemanager.frontend.service.store.StoreService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

/**
 * @author YoucTagh
 */
public class StoreModelImpl implements StoreModel {

    private final StoreService storeService;

    public StoreModelImpl(StoreService storeService) {
        this.storeService = storeService;
    }


    public ObservableList<Store> getStoreList() {
        return FXCollections.observableArrayList(storeService.findAll());
    }

    @Override
    public boolean getOneStore(Long id) {
        final Optional<Store> optional = storeService.findById(id);
        if (optional.isPresent()) {
            System.out.println(optional.get().getName());
            return true;
        } else {
            System.out.println("Empty");
            return false;
        }

    }

    @Override
    public boolean addStore(ObservableList<Store> list, Store store) {
        final Optional<Store> answer = storeService.addStore(store);
        if (answer.isPresent()) {
            list.add(answer.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteStore(ObservableList<Store> list, Store store) {
        final Optional<Store> answer = storeService.deleteStore(store);
        if (answer.isPresent()) {
            list.remove(store);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateStore(ObservableList<Store> list, Store oldItem, Store newItem) {
        final Optional<Store> answer = storeService.updateStore(newItem);
        if (answer.isPresent()) {
            list.remove(oldItem);
            list.add(newItem);
            return true;
        } else {
            return false;
        }
    }
}
