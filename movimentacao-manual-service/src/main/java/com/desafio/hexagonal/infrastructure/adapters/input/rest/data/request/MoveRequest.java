package com.desafio.hexagonal.infrastructure.adapters.input.rest.data.request;

import java.math.BigDecimal;
import java.util.Date;

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
public class MoveRequest {

    private MovePKRequest id;

    private String description;
    
    private Date dtMovement;
    
    private String user;
    
    private BigDecimal amount;
    
	private String cosif;

	private String produto;
    
}

