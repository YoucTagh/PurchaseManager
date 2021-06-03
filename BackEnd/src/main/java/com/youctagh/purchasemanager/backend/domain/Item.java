package com.youctagh.purchasemanager.backend.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Item extends BaseEntity {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Ticket ticket;

    private Double price;

    private String comment;
}
