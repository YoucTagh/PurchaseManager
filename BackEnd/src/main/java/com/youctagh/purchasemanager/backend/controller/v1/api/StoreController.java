package com.youctagh.purchasemanager.backend.controller.v1.api;


import com.youctagh.purchasemanager.backend.controller.v1.dto.mapper.StoreMapper;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.StoreDTO;
import com.youctagh.purchasemanager.backend.controller.v1.dto.response.Response;
import com.youctagh.purchasemanager.backend.controller.v1.request.StoreRequest;
import com.youctagh.purchasemanager.backend.domain.Store;
import com.youctagh.purchasemanager.backend.service.StoreServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author YoucTagh
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(maxAge = 3600)
public class StoreController {


    private final StoreServiceImpl storeService;

    public StoreController(StoreServiceImpl storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/store")
    public Response getAllStores() {
        return Response
                .ok()
                .setPayload(storeService.findAll());
    }

    @GetMapping("/store/{id}")
    public Response getOneStore(@PathVariable Long id) {
        return Response
                .ok()
                .setPayload(storeService.findById(id));
    }

    @PostMapping("/store")
    public Response addNewStore(@RequestBody StoreRequest storeRequest) {
        return Response
                .ok()
                .setPayload(addStore(storeRequest));
    }

    private StoreDTO addStore(StoreRequest storeRequest) {

        return storeService
                .addStore(StoreMapper.requestToStore(storeRequest))
                .get();
    }

    @DeleteMapping("/store")
    public Response deleteAStore(@RequestBody StoreRequest storeRequest) {
        return Response
                .ok()
                .setPayload(deleteStore(storeRequest));
    }

    private Optional<StoreDTO> deleteStore(StoreRequest storeRequest) {
        return storeService.deleteStore(
                (Store) new Store()
                        .setId(storeRequest.getId()));
    }

    @PutMapping("/store")
    public Response updateAStore(@RequestBody StoreRequest storeRequest) {
        return Response
                .ok()
                .setPayload(updateStore(storeRequest));
    }

    private StoreDTO updateStore(StoreRequest storeRequest) {

        return storeService
                .updateStore(StoreMapper.requestToStore(storeRequest))
                .get();
    }

}
