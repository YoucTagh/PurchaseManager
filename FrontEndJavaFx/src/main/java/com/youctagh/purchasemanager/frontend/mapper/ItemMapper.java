package com.youctagh.purchasemanager.frontend.mapper;


import com.youctagh.purchasemanager.frontend.domain.Item;
import com.youctagh.purchasemanager.frontend.domain.Product;
import com.youctagh.purchasemanager.frontend.domain.Ticket;
import com.youctagh.purchasemanager.frontend.request.ItemRequest;

/**
 * @author YoucTagh
 */

public class ItemMapper {

    public static ItemRequest toItemRequest(Item item, boolean id) {
        return (ItemRequest) new ItemRequest()
                .setPrice(item.getPrice())
                .setTicket_id((item.getTicket().getId()))
                .setProduct_id(item.getProduct().getId())
                .setComment(item.getComment())
                .setId(id ? item.getId() : null);
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
