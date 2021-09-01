package com.youctagh.purchasemanager.backend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Ticket extends BaseEntity {

    @ManyToOne
    private Store store;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ticket")
    private Set<Item> items;
}
