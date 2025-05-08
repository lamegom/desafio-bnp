package com.desafio.hexagonal.infrastructure.adapters.output.persistence.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.desafio.hexagonal.domain.model.Product;
import com.desafio.hexagonal.domain.model.ProductCosif;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.entity.ProductEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductMapper {

    private final ModelMapper mapper;

    public Product toProduct(ProductEntity entity){
        return mapper.map(entity, Product.class);
    }
    
    public List<ProductCosif> toProductCosifList(ProductEntity entity){
    	Product product = mapper.map(entity, Product.class);
    	
    	return product.getProductCosifList();
    }
    
    public ProductEntity toEntity(Product product){
        return mapper.map(product, ProductEntity.class);
    }

	public ProductMapper() {
		this.mapper = new ModelMapper();
	}

}
