package com.desafio.hexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManualMovePK {

    private Long month;

    private Long year;

    private Long release;
    
    private Long idProduct;
    
    private Long idCosif;

}
