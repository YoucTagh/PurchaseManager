package com.youctagh.purchasemanager.backend.service;


import com.youctagh.purchasemanager.backend.controller.v1.dto.mapper.StoreMapper;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.StoreDTO;
import com.youctagh.purchasemanager.backend.domain.Store;
import com.youctagh.purchasemanager.backend.exception.EntityType;
import com.youctagh.purchasemanager.backend.exception.ExceptionController;
import com.youctagh.purchasemanager.backend.exception.ExceptionType;
import com.youctagh.purchasemanager.backend.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author YoucTagh
 */
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Optional<StoreDTO> findById(Long id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        if (storeOptional.isPresent())
            return Optional.ofNullable(StoreMapper.toStoreDTO(storeOptional.get()));

        throw ExceptionController.throwException(EntityType.STORE,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id));
    }

    @Override
    public HashSet<StoreDTO> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(StoreMapper::toStoreDTO)
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Optional<StoreDTO> addStore(Store store) {
        Optional<Store> storeOptional = storeRepository.findByName(store.getName());
        if (storeOptional.isEmpty()) {
            return Optional.ofNullable(StoreMapper.toStoreDTO(storeRepository.save(store)));
        }
        throw ExceptionController.throwException(EntityType.STORE,
                ExceptionType.DUPLICATE_ENTITY, String.valueOf(storeOptional.get().getName()));

    }

    @Transactional
    @Override
    public Optional<StoreDTO> deleteStore(Store store) {
        Optional<Store> storeOptional = storeRepository.findById(store.getId());

        if (storeOptional.isPresent()) {
            storeRepository.deleteById(storeOptional.get().getId());
            return Optional.ofNullable(StoreMapper.toStoreDTO(storeOptional.get()));
        }
        throw ExceptionController.throwException(EntityType.STORE,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(store.getId()));
    }

    @Override
    public Optional<StoreDTO> updateStore(Store store) {
        Optional<Store> storeOptional = storeRepository.findById(store.getId());
        if (storeOptional.isPresent()) {
            Optional<Store> storeOptionalDuplication = storeRepository.findByName(store.getName());
            if (storeOptionalDuplication.isEmpty()) {
                return Optional.ofNullable(StoreMapper.toStoreDTO(storeRepository.save(store)));
            }
            throw ExceptionController.throwException(EntityType.STORE,
                    ExceptionType.DUPLICATE_ENTITY, String.valueOf(storeOptional.get().getId()));
        }
        throw ExceptionController.throwException(EntityType.STORE,
                ExceptionType.ENTITY_NOT_FOUND, String.valueOf(store.getId()));
    }
}
