package com.youctagh.purchasemanager.backend.controller.v1.dto.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDTO extends BaseEntityDTO {

    private ProductDTO product;

    private TicketDTO ticket;

    private Double price;

    private String comment;
}
