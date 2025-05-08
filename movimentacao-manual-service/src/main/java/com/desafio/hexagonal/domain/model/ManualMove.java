package com.desafio.hexagonal.domain.model;

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
public class ManualMove {

    private ManualMovePK id;

    private String description;
    
    private Date dtMovement;
    
    private String user;
    
    private BigDecimal amount;

}
