package com.youctagh.purchasemanager.backend.repository;

import com.youctagh.purchasemanager.backend.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface StoreRepository extends JpaRepository<Store,Long> {
    Optional<Store> findByName(String name);
}
