package com.youctagh.purchasemanager.frontend.model.store;

import com.youctagh.purchasemanager.frontend.domain.Store;
import javafx.collections.ObservableList;

/**
 * @author YoucTagh
 */
public interface StoreModel {
    ObservableList<Store> getStoreList();

    boolean getOneStore(Long id);

    boolean addStore(ObservableList<Store> list, Store store);

    boolean deleteStore(ObservableList<Store> list, Store store);

    boolean updateStore(ObservableList<Store> list, Store oldItem, Store newItem);
}
