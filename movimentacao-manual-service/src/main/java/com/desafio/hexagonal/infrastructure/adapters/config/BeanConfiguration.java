package com.desafio.hexagonal.infrastructure.adapters.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.desafio.hexagonal.domain.service.MoveService;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.MovePersistenceAdapter;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.mapper.MoveMapper;

/**
 * Configuracion BEANS
 */
@Configuration
public class BeanConfiguration {
    
    @Bean
    public ModelMapper model(){
        return new ModelMapper();
    }

    @Bean
    public MoveMapper moveMapper(){
        return new MoveMapper();
    }
    
    @Bean
    public MovePersistenceAdapter movePersistenceAdapter(MoveMapper moveMapper) {
        return new MovePersistenceAdapter( moveMapper);
    }

    @Bean
    public MoveService moveService(MovePersistenceAdapter movePersistenceAdapter) {
        return new MoveService(movePersistenceAdapter);
    }
    



}
