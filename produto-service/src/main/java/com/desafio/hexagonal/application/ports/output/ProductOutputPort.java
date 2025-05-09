package com.desafio.hexagonal.application.ports.output;

import java.util.List;

import com.desafio.hexagonal.domain.model.Product;
import com.desafio.hexagonal.domain.model.ProductCosif;

public interface ProductOutputPort {
    
	List<Product> getAllProducts();
    
    List<ProductCosif> findAllProductCosifByProductId(Long id);

	String findProductCosifByProductIdAndCosifId(Long idProduto, Long idCosif);

	String findProductByProductIdAndCosifId(Long idProduto);
    
}
