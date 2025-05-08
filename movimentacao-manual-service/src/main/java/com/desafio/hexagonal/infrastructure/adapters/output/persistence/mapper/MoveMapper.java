package com.desafio.hexagonal.infrastructure.adapters.output.persistence.mapper;

import org.modelmapper.ModelMapper;

import com.desafio.hexagonal.domain.model.ManualMove;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.entity.ManualMoveEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MoveMapper {

    private final ModelMapper mapper;
    
	public MoveMapper() {
		this.mapper = new ModelMapper();
	}

    public ManualMove toManualMove(ManualMoveEntity entity){
        return mapper.map(entity, ManualMove.class);
    }
    
    public ManualMoveEntity toEntity(ManualMove move){
        return mapper.map(move, ManualMoveEntity.class);
    }

}
