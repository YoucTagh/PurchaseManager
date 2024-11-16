package com.youctagh.purchasemanager.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@ToString
public class Ticket extends BaseEntity {

    @ManyToOne
    private Store store;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ticket", cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<Item> items;
}
