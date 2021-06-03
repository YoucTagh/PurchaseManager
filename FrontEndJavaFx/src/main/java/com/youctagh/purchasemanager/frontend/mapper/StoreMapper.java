package com.youctagh.purchasemanager.frontend.mapper;


import com.youctagh.purchasemanager.frontend.domain.Store;
import com.youctagh.purchasemanager.frontend.request.StoreRequest;

/**
 * @author YoucTagh
 */

public class StoreMapper {

    public static StoreRequest toStoreRequest(Store store) {
        return (StoreRequest) new StoreRequest()
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
