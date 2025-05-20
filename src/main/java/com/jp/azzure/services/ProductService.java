package com.jp.azzure.services; // Corrigi a capitalização do pacote para seguir a convenção

import com.jp.azzure.domain.product.Product;
import com.jp.azzure.repository.ProductRepository; // Corrigi a capitalização do pacote para seguir a convenção
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service 
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        if(product.getId() != null && productRepository.existsById(product.getId())) {
            return null; 
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return;
        }
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}