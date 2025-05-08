package com.desafio.hexagonal.infrastructure.adapters.output.persistence;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.desafio.hexagonal.application.ports.output.ProductOutputPort;
import com.desafio.hexagonal.domain.model.Product;
import com.desafio.hexagonal.domain.model.ProductCosif;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.entity.ProductEntity;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductOutputPort {

    private final ProductMapper productMapper;
    
    @PersistenceContext
    private EntityManager em;

    @Transactional
	@Override
	public List<Product> getAllProducts() {
    	TypedQuery<ProductEntity> query = em.createQuery("SELECT u FROM ProductEntity u", ProductEntity.class);
    	List<ProductEntity> productList = query.getResultList();
    	return productList.stream()
	    	.map(productEntity -> productMapper.toProduct(productEntity))
	    	.collect(Collectors.toList());
	}

	@Override
	public List<ProductCosif> findAllProductCosifByProductId(Long id) {
		TypedQuery<ProductEntity> query = em.createQuery("SELECT u FROM ProductEntity u WHERE u.id = :id", ProductEntity.class);
		query.setParameter("id", id);
		ProductEntity product = query.getResultList().stream().findFirst().orElse(null);
    	return productMapper.toProductCosifList(product);
	}
    
}
