package com.desafio.hexagonal.infrastructure.adapters.input.rest.data.request;

import java.io.Serializable;

import com.desafio.hexagonal.infrastructure.adapters.output.persistence.entity.ManualMovePKEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MovePKRequest implements Serializable{

    private Long month;

    private Long year;

    private Long release;
    
    private Long idProduct;
    
    private Long idCosif;
    
}

