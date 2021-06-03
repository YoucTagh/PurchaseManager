package com.youctagh.purchasemanager.frontend.service.base;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public interface BaseService {
    Optional findById(Long id, String relativeURL, Class clazz);

    HashSet findAll(String relativeURL, Class clazz);

    Optional addObject(Object object, String relativeURL, Class clazz);

    Optional deleteObject(Object object, String relativeURL, Class clazz);

    Optional updateObject(Object object, String relativeURL, Class clazz);
}
