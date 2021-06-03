package com.youctagh.purchasemanager.backend.service;


import com.youctagh.purchasemanager.backend.controller.v1.dto.model.StoreDTO;
import com.youctagh.purchasemanager.backend.domain.Store;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface StoreService {
    Optional<StoreDTO> findById(Long id);
    HashSet<StoreDTO> findAll();
    Optional<StoreDTO> addStore(Store store);
    Optional<StoreDTO> deleteStore(Store store);
    Optional<StoreDTO> updateStore(Store store);
}
