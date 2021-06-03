package com.youctagh.purchasemanager.backend.service;


import com.youctagh.purchasemanager.backend.controller.v1.dto.model.TicketDTO;
import com.youctagh.purchasemanager.backend.domain.Ticket;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface TicketService {
    Optional<TicketDTO> findById(Long id);

    HashSet<TicketDTO> findAll();

    Optional<TicketDTO> addTicket(Ticket ticke);

    Optional<TicketDTO> deleteTicket(Ticket ticket);

    Optional<TicketDTO> updateTicket(Ticket ticket);
}
