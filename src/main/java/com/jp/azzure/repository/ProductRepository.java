package com.jp.azzure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.azzure.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
}
