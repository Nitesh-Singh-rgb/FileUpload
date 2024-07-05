package com.springapi.uploading.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springapi.uploading.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	
}
