package com.phnm.laptopshop.service;

import com.phnm.laptopshop.domain.Product;
import com.phnm.laptopshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product newProduct) {
        productRepository.save(newProduct);
    }

    public Product getProductById(long id) {
        return productRepository.findProductById(id);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }
}
