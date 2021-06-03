package com.youctagh.purchasemanager.backend.controller.v1.request;


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
public class ItemRequest extends BaseEntityRequest {

    private Long product_id;

    private Long ticket_id;

    private Double price;

    private String comment;
}
