package com.youctagh.purchasemanager.frontend.service.ticket;

import com.youctagh.purchasemanager.frontend.domain.Ticket;
import com.youctagh.purchasemanager.frontend.request.TicketRequest;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface TicketService {
    Optional<Ticket> findById(Long id);

    HashSet<Ticket> findAll();

    Optional<Ticket> addTicket(TicketRequest request);

    Optional<Ticket> deleteTicket(Ticket request);

    Optional<Ticket> updateTicket(TicketRequest request);
}
