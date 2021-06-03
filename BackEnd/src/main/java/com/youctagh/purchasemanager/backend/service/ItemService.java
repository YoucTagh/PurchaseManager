package com.youctagh.purchasemanager.backend.service;

import com.youctagh.purchasemanager.backend.controller.v1.dto.model.ItemDTO;
import com.youctagh.purchasemanager.backend.domain.Item;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface ItemService {
    Optional<ItemDTO> findById(Long id);
    HashSet<ItemDTO> findAll();
    Optional<Item> addItem(Item item);
    Optional<ItemDTO> deleteItem(Item item);
    Optional<ItemDTO> updateItem(Item item);
}
