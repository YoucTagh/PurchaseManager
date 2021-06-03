package com.youctagh.purchasemanager.backend.repository;

import com.youctagh.purchasemanager.backend.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
