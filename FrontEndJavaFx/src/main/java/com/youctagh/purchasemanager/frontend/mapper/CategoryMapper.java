package com.youctagh.purchasemanager.frontend.mapper;

import com.youctagh.purchasemanager.frontend.domain.Category;
import com.youctagh.purchasemanager.frontend.request.CategoryRequest;

/**
 * @author YoucTagh
 */

public class CategoryMapper {

    public static CategoryRequest toCategoryRequest(Category category) {
        return (CategoryRequest) new CategoryRequest()
                .setName(category.getName())
                .setId(category.getId());
    }

    public static Category requestToCategory(CategoryRequest request) {
        return (Category) new Category()
                .setName(request.getName())
                .setId(request.getId());
    }
}
