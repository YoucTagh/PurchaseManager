package com.youctagh.purchasemanager.backend.controller.v1.api;


import com.youctagh.purchasemanager.backend.controller.v1.dto.mapper.CategoryMapper;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.CategoryDTO;
import com.youctagh.purchasemanager.backend.controller.v1.dto.response.Response;
import com.youctagh.purchasemanager.backend.controller.v1.request.CategoryRequest;
import com.youctagh.purchasemanager.backend.domain.Category;
import com.youctagh.purchasemanager.backend.service.CategoryServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author YoucTagh
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(maxAge = 3600)
public class CategoryController {


    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public Response getAllCategorys() {
        return Response
                .ok()
                .setPayload(categoryService.findAll());
    }

    @GetMapping("/category/{id}")
    public Response getOneCategory(@PathVariable Long id) {
        return Response
                .ok()
                .setPayload(categoryService.findById(id));
    }

    @PostMapping("/category")
    public Response addNewCategory(@RequestBody CategoryRequest categoryRequest) {
        return Response
                .ok()
                .setPayload(addCategory(categoryRequest));
    }

    private CategoryDTO addCategory(CategoryRequest categoryRequest) {

        return categoryService.addCategory(CategoryMapper.requestToCategory(categoryRequest))
                        .get();
    }

    @DeleteMapping("/category")
    public Response deleteACategory(@RequestBody CategoryRequest categoryRequest) {
        return Response
                .ok()
                .setPayload(deleteCategory(categoryRequest));
    }

    private Optional<CategoryDTO> deleteCategory(CategoryRequest categoryRequest) {
        return categoryService.deleteCategory(
                (Category) new Category()
                        .setId(categoryRequest.getId()));
    }

    @PutMapping("/category")
    public Response updateACategory(@RequestBody CategoryRequest categoryRequest) {
        return Response
                .ok()
                .setPayload(updateCategory(categoryRequest));
    }

    private CategoryDTO updateCategory(CategoryRequest categoryRequest) {

        return categoryService.updateCategory(
                (Category) new Category()
                        .setName(categoryRequest.getName())
                        .setId(categoryRequest.getId()))
                .get();
    }

}
