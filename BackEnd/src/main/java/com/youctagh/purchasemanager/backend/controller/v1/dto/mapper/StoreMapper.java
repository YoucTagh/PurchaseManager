package com.youctagh.purchasemanager.backend.controller.v1.dto.mapper;

import com.youctagh.purchasemanager.backend.controller.v1.dto.model.StoreDTO;
import com.youctagh.purchasemanager.backend.controller.v1.request.StoreRequest;
import com.youctagh.purchasemanager.backend.domain.Store;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author YoucTagh
 */
@Component
public class StoreMapper {

    public static StoreDTO toStoreDTO(Store store) {
        return (StoreDTO) new StoreDTO()
                .setName(store.getName())
                .setAddress(store.getAddress())
                .setId(store.getId());
    }

    public static Store requestToStore(StoreRequest request) {
        return (Store) new Store()
                .setName(request.getName())
                .setAddress(request.getAddress())
                .setId(request.getId());
    }
}
