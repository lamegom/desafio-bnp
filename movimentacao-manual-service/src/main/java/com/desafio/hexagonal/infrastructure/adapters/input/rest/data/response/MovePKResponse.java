package com.desafio.hexagonal.infrastructure.adapters.input.rest.data.response;

import java.math.BigDecimal;
import java.util.Date;

import com.desafio.hexagonal.infrastructure.adapters.input.rest.data.request.MovePKRequest;

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
public class MovePKResponse {
    
    private Long month;

    private Long year;

    private Long release;
    
    private Long idProduct;
    
    private Long idCosif;
}
