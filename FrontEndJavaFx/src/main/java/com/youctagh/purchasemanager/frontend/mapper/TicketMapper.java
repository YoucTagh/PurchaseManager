package com.youctagh.purchasemanager.frontend.mapper;

import com.youctagh.purchasemanager.frontend.domain.Item;
import com.youctagh.purchasemanager.frontend.domain.Store;
import com.youctagh.purchasemanager.frontend.domain.Ticket;
import com.youctagh.purchasemanager.frontend.request.TicketRequest;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author YoucTagh
 */

public class TicketMapper {

    public static TicketRequest toTicketRequest(Ticket ticket, boolean includeItems) {
        final TicketRequest ticketRequest = (TicketRequest) new TicketRequest()
                .setStore_id(ticket.getStore().getId())
                .setDate(ticket.getDate())
                .setId(ticket.getId());
        if (includeItems)
            ticketRequest.setItems(Optional.ofNullable(ticket.getItems())
                    .orElseGet(Collections::emptySet)
                    .stream()
                    .map((Item item) -> ItemMapper.toItemRequest(item, true))
                    .collect(Collectors.toSet()));
        return ticketRequest;
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
