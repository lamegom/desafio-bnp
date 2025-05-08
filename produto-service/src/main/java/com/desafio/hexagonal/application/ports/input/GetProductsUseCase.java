package com.desafio.hexagonal.application.ports.input;

import java.util.List;

import com.desafio.hexagonal.domain.model.Product;
import com.desafio.hexagonal.domain.model.ProductCosif;

public interface GetProductsUseCase {
	List<Product> getAllProducts();
    
    List<ProductCosif> findAllProductCosifByProductId(Long id);
}
