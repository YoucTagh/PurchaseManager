package com.youctagh.purchasemanager.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Product extends BaseEntity{

    private String name;

    @ManyToOne
    private Category category;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "product")
    private Set<Item> items;
}
