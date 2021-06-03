package com.youctagh.purchasemanager.backend.controller.v1.api;


import com.youctagh.purchasemanager.backend.controller.v1.dto.mapper.ProductMapper;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.ProductDTO;
import com.youctagh.purchasemanager.backend.controller.v1.dto.response.Response;
import com.youctagh.purchasemanager.backend.controller.v1.request.ProductRequest;
import com.youctagh.purchasemanager.backend.domain.Category;
import com.youctagh.purchasemanager.backend.domain.Product;
import com.youctagh.purchasemanager.backend.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author YoucTagh
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(maxAge = 3600)
public class ProductController {


    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public Response getAllProducts() {
        return Response
                .ok()
                .setPayload(productService.findAll());
    }

    @GetMapping("/product/{id}")
    public Response getOneProduct(@PathVariable Long id) {
        return Response
                .ok()
                .setPayload(productService.findById(id));
    }

    @PostMapping("/product")
    public Response addNewProduct(@RequestBody ProductRequest productRequest) {
        return Response
                .ok()
                .setPayload(addProduct(productRequest));
    }

    private ProductDTO addProduct(ProductRequest productRequest) {

        return productService
                .addProduct(ProductMapper.requestToProduct(productRequest))
                .get();
    }

    @DeleteMapping("/product")
    public Response deleteAProduct(@RequestBody ProductRequest productRequest) {
        return Response
                .ok()
                .setPayload(deleteProduct(productRequest));
    }

    private Optional<ProductDTO> deleteProduct(ProductRequest productRequest) {
        return productService.deleteProduct(
                (Product) new Product()
                        .setId(productRequest.getId()));
    }

    @PutMapping("/product")
    public Response updateAProduct(@RequestBody ProductRequest productRequest) {
        return Response
                .ok()
                .setPayload(updateProduct(productRequest));
    }

    private ProductDTO updateProduct(ProductRequest productRequest) {

        return productService.updateProduct(
                ProductMapper.requestToProduct(productRequest))
                .get();
    }

}
