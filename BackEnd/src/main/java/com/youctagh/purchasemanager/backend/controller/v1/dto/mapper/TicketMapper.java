package com.youctagh.purchasemanager.backend.controller.v1.dto.mapper;

import com.youctagh.purchasemanager.backend.controller.v1.dto.model.TicketDTO;
import com.youctagh.purchasemanager.backend.controller.v1.request.TicketRequest;
import com.youctagh.purchasemanager.backend.domain.Store;
import com.youctagh.purchasemanager.backend.domain.Ticket;
import com.youctagh.purchasemanager.backend.util.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author YoucTagh
 */
@Component
public class TicketMapper {

    public static TicketDTO toTicketDTO(Ticket ticket) {
        return (TicketDTO) new TicketDTO()
                .setStore(StoreMapper.toStoreDTO(ticket.getStore()))
                .setDate(DateUtils.formattedDate(ticket.getDate()))
                .setItems(Optional.ofNullable(ticket.getItems())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(ItemMapper::toItemDTO)
                        .collect(Collectors.toSet()))
                .setId(ticket.getId());
    }

    public static Ticket requestToTicket(TicketRequest request) {
        return (Ticket) new Ticket()
                .setStore((Store) new Store()
                        .setId(request.getStore_id()))
                .setDate(request.getDate())
                .setItems(Optional.ofNullable(request.getItems()).orElseGet(Collections::emptySet)
                        .stream()
                        .map(ItemMapper::requestToItem)
                        .collect(Collectors.toSet()))
                .setId(request.getId());
    }
}
