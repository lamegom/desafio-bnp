package com.desafio.hexagonal.infrastructure.adapters.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.desafio.hexagonal.domain.service.ProductService;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.mapper.ProductMapper;

/**
 * Configuracion BEANS
 */
@Configuration
public class BeanConfiguration {
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    
    @Bean
    public ProductMapper productMapper(){
        return new ProductMapper();
    } 
    
    @Bean
    public ProductPersistenceAdapter productPersistenceAdapter( ProductMapper productMapper) {
        return new ProductPersistenceAdapter( productMapper);
    }

    @Bean
    public ProductService ProductService(ProductPersistenceAdapter productPersistenceAdapter) {
        return new ProductService(productPersistenceAdapter);
    }

}
