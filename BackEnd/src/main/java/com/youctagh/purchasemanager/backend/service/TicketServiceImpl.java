package com.youctagh.purchasemanager.backend.service;

import com.youctagh.purchasemanager.backend.controller.v1.dto.mapper.TicketMapper;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.TicketDTO;
import com.youctagh.purchasemanager.backend.domain.Item;
import com.youctagh.purchasemanager.backend.domain.Store;
import com.youctagh.purchasemanager.backend.domain.Ticket;
import com.youctagh.purchasemanager.backend.exception.EntityType;
import com.youctagh.purchasemanager.backend.exception.ExceptionController;
import com.youctagh.purchasemanager.backend.exception.ExceptionType;
import com.youctagh.purchasemanager.backend.repository.StoreRepository;
import com.youctagh.purchasemanager.backend.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author YoucTagh
 */
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final StoreRepository storeRepository;
    private final ItemService itemService;

    public TicketServiceImpl(TicketRepository ticketRepository, StoreRepository storeRepository,
                             ItemService itemService) {
        this.ticketRepository = ticketRepository;
        this.storeRepository = storeRepository;
        this.itemService = itemService;
    }

    @Override
    public Optional<TicketDTO> findById(Long id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isPresent())
            return Optional.ofNullable(TicketMapper.toTicketDTO(ticketOptional.get()));

        throw ExceptionController.throwException(EntityType.TICKET,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id));
    }

    @Override
    public HashSet<TicketDTO> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(TicketMapper::toTicketDTO)
                .collect(Collectors.toCollection(HashSet::new));
    }


    @Override
    public Optional<TicketDTO> addTicket(Ticket ticket) {

        Optional<Store> storeOptional = storeRepository.findById(ticket.getStore().getId());

        if (storeOptional.isPresent()) {
            ticket.setStore(storeOptional.get());

            Set<Item> items = new HashSet<>();
            final Set<Item> itemsToSave = new HashSet<>(ticket.getItems());

            ticket.getItems().clear();

            final Ticket saved = ticketRepository.save(ticket);

            ticket.setId(saved.getId());

            itemsToSave.forEach(item -> {
                item.setTicket(ticket);
                final Optional<Item> savedItem = itemService.addItem(item);
                savedItem.ifPresent(items::add);
            });

            ticket.setItems(items);

            return Optional.ofNullable(TicketMapper.toTicketDTO(ticket));
        }
        throw ExceptionController.throwException(EntityType.STORE,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(ticket.getStore().getId()));

    }

    @Override
    public Optional<TicketDTO> deleteTicket(Ticket ticket) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticket.getId());

        if (ticketOptional.isPresent()) {
            ticketRepository.deleteById(ticketOptional.get().getId());
            return Optional.ofNullable(TicketMapper.toTicketDTO(ticketOptional.get()));
        }
        throw ExceptionController.throwException(EntityType.TICKET,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(ticket.getId()));
    }

    @Override
    public Optional<TicketDTO> updateTicket(Ticket ticket) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticket.getId());
        if (ticketOptional.isPresent()) {
            final Ticket ticketOld = ticketOptional.get();
            ticketOld.getItems().forEach(itemService::deleteItem);
            Set<Item> items = new HashSet<>(ticket.getItems());
            ticket.getItems().clear();

            Optional<Store> storeOptional = storeRepository.findById(ticket.getStore().getId());
            if (storeOptional.isPresent()) {
                ticket.setStore(storeOptional.get());
                ticket = ticketRepository.save(ticket);
                for (Item item : items) {
                    item.setTicket(ticket);
                    final Optional<Item> optionalItem = itemService.addItem(item);
                    if (optionalItem.isPresent()) {
                        ticket.getItems().add(optionalItem.get());
                    }
                }
                return Optional.ofNullable(TicketMapper.toTicketDTO(ticket));
            }
            throw ExceptionController.throwException(EntityType.STORE,
                    ExceptionType.ENTITY_NOT_FOUND, String.valueOf(ticket.getStore().getId()));

        }
        throw ExceptionController.throwException(EntityType.TICKET,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(ticket.getId()));
    }

}
