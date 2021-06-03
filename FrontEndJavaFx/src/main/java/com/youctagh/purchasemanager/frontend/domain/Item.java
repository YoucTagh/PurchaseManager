package com.youctagh.purchasemanager.frontend.domain;

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
public class Item extends BaseEntity {
    private Product product;
    private Ticket ticket;
    private Double price;
    private String comment;

    public String getProductName() {
        if (product != null)
            return product.getName();
        else
            return "";
    }
}
