package ua.hehmde.kvjewelry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.hehmde.kvjewelry.entity.Product;
import ua.hehmde.kvjewelry.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getNewProducts(int limit) {
        return productRepository.findByIdIn(
                productRepository.findIdByVendorcodeNotNullOrderByCreatedDesc(
                        PageRequest.of(0, limit)
                )
        );
    }
}
