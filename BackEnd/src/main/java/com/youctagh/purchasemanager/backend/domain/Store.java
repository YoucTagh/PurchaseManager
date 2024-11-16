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
public class Store extends BaseEntity {

    private String name;
    private String address;

    @OneToMany(mappedBy = "store")
    private Set<Ticket> tickets;
}
