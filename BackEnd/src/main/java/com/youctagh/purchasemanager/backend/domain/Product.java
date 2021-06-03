package com.youctagh.purchasemanager.backend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
