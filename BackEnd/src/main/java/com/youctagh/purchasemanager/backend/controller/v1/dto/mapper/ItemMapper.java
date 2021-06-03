package com.youctagh.purchasemanager.backend.controller.v1.dto.mapper;

import com.youctagh.purchasemanager.backend.controller.v1.dto.model.ItemDTO;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.TicketDTO;
import com.youctagh.purchasemanager.backend.controller.v1.request.ItemRequest;
import com.youctagh.purchasemanager.backend.domain.Item;
import com.youctagh.purchasemanager.backend.domain.Product;
import com.youctagh.purchasemanager.backend.domain.Ticket;
import org.springframework.stereotype.Component;

/**
 * @author YoucTagh
 */
@Component
public class ItemMapper {

    public static ItemDTO toItemDTO(Item item) {
        return (ItemDTO) new ItemDTO()
                .setPrice(item.getPrice())
                .setTicket(item.getTicket() == null ? null : (TicketDTO) new TicketDTO().setId(item.getTicket().getId()))
                .setProduct((item.getProduct() == null) ? null : ProductMapper.toProductDTO(item.getProduct()))
                .setComment(item.getComment())
                .setId(item.getId());
    }


    public static Item requestToItem(ItemRequest request) {
        return (Item) new Item()
                .setPrice(request.getPrice())
                .setTicket((request.getTicket_id() == null) ? null : (Ticket) new Ticket().setId(request.getTicket_id()))
                .setProduct((request.getProduct_id() == null) ? null : (Product) new Product().setId(request.getProduct_id()))
                .setComment(request.getComment())
                .setId(request.getId());
    }

}
