package com.youctagh.purchasemanager.frontend.model.ticket;

import com.youctagh.purchasemanager.frontend.domain.Product;
import com.youctagh.purchasemanager.frontend.domain.Store;
import com.youctagh.purchasemanager.frontend.domain.Ticket;
import com.youctagh.purchasemanager.frontend.request.TicketRequest;
import javafx.collections.ObservableList;

/**
 * @author YoucTagh
 */
public interface TicketModel {
    ObservableList<Ticket> getTicketList();

    boolean getOneTicket(Long id);

    boolean addTicket(ObservableList<Ticket> list, TicketRequest request);

    boolean deleteTicket(ObservableList<Ticket> list, Ticket ticket);

    boolean updateTicket(ObservableList<Ticket> list, Ticket oldItem, TicketRequest request);

}
