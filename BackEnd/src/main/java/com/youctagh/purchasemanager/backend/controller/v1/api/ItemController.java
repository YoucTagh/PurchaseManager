package com.youctagh.purchasemanager.backend.controller.v1.api;


import com.youctagh.purchasemanager.backend.controller.v1.dto.mapper.ItemMapper;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.ItemDTO;
import com.youctagh.purchasemanager.backend.controller.v1.dto.response.Response;
import com.youctagh.purchasemanager.backend.controller.v1.request.ItemRequest;
import com.youctagh.purchasemanager.backend.domain.Item;
import com.youctagh.purchasemanager.backend.service.ItemServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author YoucTagh
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(maxAge = 3600)
public class ItemController {


    private final ItemServiceImpl itemService;

    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/item")
    public Response getAllItems() {
        return Response
                .ok()
                .setPayload(itemService.findAll());
    }

    @GetMapping("/item/{id}")
    public Response getOneItem(@PathVariable Long id) {
        return Response
                .ok()
                .setPayload(itemService.findById(id));
    }

    @DeleteMapping("/item")
    public Response deleteAItem(@RequestBody ItemRequest itemRequest) {
        return Response
                .ok()
                .setPayload(deleteItem(itemRequest));
    }

    private Optional<ItemDTO> deleteItem(ItemRequest itemRequest) {
        return itemService.deleteItem(
                (Item) new Item()
                        .setId(itemRequest.getId()));
    }

    @PutMapping("/item")
    public Response updateAItem(@RequestBody ItemRequest itemRequest) {
        return Response
                .ok()
                .setPayload(updateItem(itemRequest));
    }

    private ItemDTO updateItem(ItemRequest itemRequest) {

        return itemService
                .updateItem(ItemMapper.requestToItem(itemRequest))
                .get();
    }

}
