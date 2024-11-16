package com.youctagh.purchasemanager.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Category extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
