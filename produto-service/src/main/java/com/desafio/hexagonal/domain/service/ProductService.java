package com.desafio.hexagonal.domain.service;

import java.util.List;

import com.desafio.hexagonal.application.ports.input.GetProductsUseCase;
import com.desafio.hexagonal.application.ports.output.ProductOutputPort;
import com.desafio.hexagonal.domain.model.Product;
import com.desafio.hexagonal.domain.model.ProductCosif;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService implements GetProductsUseCase{

    private final ProductOutputPort productOutputPort;

	@Override
	public List<Product> getAllProducts() {
		
		return productOutputPort.getAllProducts();
	}

	@Override
	public List<ProductCosif> findAllProductCosifByProductId(Long id) {
		return productOutputPort.findAllProductCosifByProductId(id);
	}

    
}
