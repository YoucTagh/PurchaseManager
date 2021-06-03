package com.youctagh.purchasemanager.backend.controller.v1.dto.mapper;

import com.youctagh.purchasemanager.backend.controller.v1.dto.model.CategoryDTO;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.ProductDTO;
import com.youctagh.purchasemanager.backend.controller.v1.request.CategoryRequest;
import com.youctagh.purchasemanager.backend.domain.Category;
import com.youctagh.purchasemanager.backend.domain.Product;
import org.springframework.stereotype.Component;

/**
 * @author YoucTagh
 */
@Component
public class CategoryMapper {

    public static CategoryDTO toCategoryDTO(Category category) {
        return (CategoryDTO) new CategoryDTO()
                .setName(category.getName())
                .setId(category.getId());
    }

    public static Category requestToCategory(CategoryRequest request) {
        return (Category) new Category()
                .setName(request.getName())
                .setId(request.getId());
    }
}
