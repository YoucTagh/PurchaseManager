package com.youctagh.purchasemanager.backend.repository;

import com.youctagh.purchasemanager.backend.domain.Item;
import com.youctagh.purchasemanager.backend.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface ItemRepository extends JpaRepository<Item,Long> {
}
