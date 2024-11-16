package com.youctagh.purchasemanager.backend.controller.v1.dto.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
