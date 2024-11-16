package com.youctagh.purchasemanager.frontend.model.ticket;

import com.youctagh.purchasemanager.frontend.domain.Ticket;
import com.youctagh.purchasemanager.frontend.request.TicketRequest;
import com.youctagh.purchasemanager.frontend.service.product.ProductService;
import com.youctagh.purchasemanager.frontend.service.store.StoreService;
import com.youctagh.purchasemanager.frontend.service.ticket.TicketService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

/**
 * @author YoucTagh
 */
public class TicketModelImpl implements TicketModel {

    private final TicketService ticketService;

    private final ProductService productService;
    private final StoreService storeService;


    public TicketModelImpl(TicketService ticketService, ProductService productService, StoreService storeService) {
        this.ticketService = ticketService;
        this.productService = productService;
        this.storeService = storeService;
    }

    public ObservableList<Ticket> getTicketList() {
        return FXCollections.observableArrayList(ticketService.findAll());
    }

    @Override
    public boolean getOneTicket(Long id) {
        final Optional<Ticket> optional = ticketService.findById(id);
        if (optional.isPresent()) {
            System.out.println(optional.get().getId());
            return true;
        } else {
            System.out.println("Empty");
            return false;
        }

    }

    @Override
    public boolean addTicket(ObservableList<Ticket> list, TicketRequest request) {
        final Optional<Ticket> answer = ticketService.addTicket(request);
        if (answer.isPresent()) {
            list.add(answer.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteTicket(ObservableList<Ticket> list, Ticket ticket) {
        final Optional<Ticket> answer = ticketService.deleteTicket(ticket);
        if (answer.isPresent()) {
            list.remove(ticket);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateTicket(ObservableList<Ticket> list, Ticket oldItem, TicketRequest request) {
        final Optional<Ticket> answer = ticketService.updateTicket(request);
        if (answer.isPresent()) {
            list.remove(oldItem);
            list.add(answer.get());
            return true;
        } else {
            return false;
        }
    }
}
