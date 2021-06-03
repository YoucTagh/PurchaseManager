package com.youctagh.purchasemanager.backend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
