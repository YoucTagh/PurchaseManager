package com.youctagh.purchasemanager.backend.repository;

import com.youctagh.purchasemanager.backend.domain.Product;
import com.youctagh.purchasemanager.backend.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
}
