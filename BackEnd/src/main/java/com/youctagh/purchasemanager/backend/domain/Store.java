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
public class Store extends BaseEntity {

    private String name;
    private String address;

    @OneToMany(mappedBy = "store")
    private Set<Ticket> tickets;
}
