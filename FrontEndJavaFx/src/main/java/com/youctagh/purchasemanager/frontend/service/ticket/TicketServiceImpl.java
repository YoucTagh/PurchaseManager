package com.youctagh.purchasemanager.frontend.service.ticket;

import com.youctagh.purchasemanager.frontend.domain.Ticket;
import com.youctagh.purchasemanager.frontend.request.TicketRequest;
import com.youctagh.purchasemanager.frontend.service.base.BaseServiceImpl;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public class TicketServiceImpl extends BaseServiceImpl implements TicketService {
    private final String relativeURL = "/ticket";

    @Override
    public Optional<Ticket> findById(Long id) {
        return super.findById(id, relativeURL, Ticket.class);
    }

    @Override
    public HashSet<Ticket> findAll() {
        return super.findAll(relativeURL, Ticket.class);
    }

    @Override
    public Optional<Ticket> addTicket(TicketRequest request) {
        return super.addObject(request, relativeURL, Ticket.class);
    }

    @Override
    public Optional<Ticket> deleteTicket(Ticket ticket) {
        return super.deleteObject(ticket, relativeURL, Ticket.class);
    }

    @Override
    public Optional<Ticket> updateTicket(TicketRequest request) {
        return super.updateObject(request, relativeURL, Ticket.class);
    }
}
