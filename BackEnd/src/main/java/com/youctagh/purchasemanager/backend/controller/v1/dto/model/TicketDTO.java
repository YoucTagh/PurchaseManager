package com.youctagh.purchasemanager.backend.controller.v1.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketDTO extends BaseEntityDTO {

    private StoreDTO store;

    private String date;

    private Set<ItemDTO> items;

}
