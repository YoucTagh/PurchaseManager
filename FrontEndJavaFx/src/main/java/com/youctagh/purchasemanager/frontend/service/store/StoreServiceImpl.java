package com.youctagh.purchasemanager.frontend.service.store;

import com.youctagh.purchasemanager.frontend.domain.Store;
import com.youctagh.purchasemanager.frontend.service.base.BaseServiceImpl;
import com.youctagh.purchasemanager.frontend.service.store.StoreService;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public class StoreServiceImpl extends BaseServiceImpl implements StoreService {
    private final String relativeURL = "/store";

    @Override
    public Optional<Store> findById(Long id) {
        return super.findById(id, relativeURL, Store.class);
    }

    @Override
    public HashSet<Store> findAll() {
        return super.findAll(relativeURL, Store.class);
    }

    @Override
    public Optional<Store> addStore(Store store) {
        return super.addObject(store, relativeURL, Store.class);
    }

    @Override
    public Optional<Store> deleteStore(Store store) {
        return super.deleteObject(store, relativeURL, Store.class);
    }

    @Override
    public Optional<Store> updateStore(Store store) {
        return super.updateObject(store, relativeURL, Store.class);
    }
}
