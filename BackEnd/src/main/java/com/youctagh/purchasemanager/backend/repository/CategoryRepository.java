package com.youctagh.purchasemanager.backend.repository;

import com.youctagh.purchasemanager.backend.domain.Category;
import com.youctagh.purchasemanager.backend.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
}
