package com.youctagh.purchasemanager.backend.service;

import com.youctagh.purchasemanager.backend.controller.v1.dto.mapper.ItemMapper;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.ItemDTO;
import com.youctagh.purchasemanager.backend.domain.Item;
import com.youctagh.purchasemanager.backend.domain.Product;
import com.youctagh.purchasemanager.backend.domain.Ticket;
import com.youctagh.purchasemanager.backend.exception.EntityType;
import com.youctagh.purchasemanager.backend.exception.ExceptionController;
import com.youctagh.purchasemanager.backend.exception.ExceptionType;
import com.youctagh.purchasemanager.backend.repository.ItemRepository;
import com.youctagh.purchasemanager.backend.repository.ProductRepository;
import com.youctagh.purchasemanager.backend.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author YoucTagh
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final TicketRepository ticketRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ProductRepository productRepository,
                           TicketRepository ticketRepository) {
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Optional<ItemDTO> findById(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent())
            return Optional.ofNullable(ItemMapper.toItemDTO(itemOptional.get()));

        throw ExceptionController.throwException(EntityType.ITEM,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id));
    }

    @Override
    public HashSet<ItemDTO> findAll() {
        return itemRepository.findAll()
                .stream()
                .map(ItemMapper::toItemDTO)
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Optional<Item> addItem(Item item) {

        Optional<Product> productOptional = productRepository.findById(item.getProduct().getId());
        if (productOptional.isPresent()) {

            Optional<Ticket> ticketOptional = ticketRepository.findById(item.getTicket().getId());
            if (ticketOptional.isPresent()) {

                item.setProduct(productOptional.get());

                final Item save = itemRepository.save(item);
                item.setId(save.getId());
                return Optional.of(item);
            }
            throw ExceptionController.throwException(EntityType.TICKET,
                    ExceptionType.ENTITY_NOT_FOUND, String.valueOf(item.getTicket().getId()));
        }
        throw ExceptionController.throwException(EntityType.PRODUCT,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(item.getProduct().getId()));
    }

    @Transactional
    @Override
    public Optional<ItemDTO> deleteItem(Item item) {
        Optional<Item> itemOptional = itemRepository.findById(item.getId());

        if (itemOptional.isPresent()) {
            itemRepository.deleteById(itemOptional.get().getId());
            return Optional.ofNullable(ItemMapper.toItemDTO(itemOptional.get()));
        }
        throw ExceptionController.throwException(EntityType.ITEM,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(item.getId()));
    }

    @Override
    public Optional<ItemDTO> updateItem(Item item) {
        Optional<Item> itemOptional = itemRepository.findById(item.getId());
        if (itemOptional.isPresent()) {
            Optional<Item> itemOptionalDuplication = itemRepository.findById(item.getId());
            if (itemOptionalDuplication.isEmpty()) {
                return Optional.ofNullable(ItemMapper.toItemDTO(itemRepository.save(item)));
            }
            throw ExceptionController.throwException(EntityType.ITEM,
                    ExceptionType.DUPLICATE_ENTITY, String.valueOf(itemOptional.get().getId()));
        }
        throw ExceptionController.throwException(EntityType.ITEM,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(item.getId()));
    }
}
