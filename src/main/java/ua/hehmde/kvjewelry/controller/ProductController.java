package ua.hehmde.kvjewelry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.hehmde.kvjewelry.entity.Product;
import ua.hehmde.kvjewelry.repository.ProductRepository;
import ua.hehmde.kvjewelry.service.ProductService;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value="/products/new", produces = "application/json")
    public List<Product> getNewProducts(@RequestParam(value = "limit") int limit) {
        return productService.getNewProducts(limit);
    }
}
