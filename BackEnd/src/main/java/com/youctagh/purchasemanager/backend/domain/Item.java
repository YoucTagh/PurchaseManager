package com.youctagh.purchasemanager.backend.domain;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Item extends BaseEntity {

    @ManyToOne
    private Product product;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ticket ticket;

    private Double price;

    private String comment;
}
