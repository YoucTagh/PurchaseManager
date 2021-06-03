package com.youctagh.purchasemanager.frontend.service.store;

import com.youctagh.purchasemanager.frontend.domain.Store;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface StoreService {
    Optional<Store> findById(Long id);
    HashSet<Store> findAll();
    Optional<Store> addStore(Store store);
    Optional<Store> deleteStore(Store store);
    Optional<Store> updateStore(Store store);
}
