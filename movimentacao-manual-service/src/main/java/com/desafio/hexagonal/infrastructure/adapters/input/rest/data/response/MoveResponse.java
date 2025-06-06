package com.desafio.hexagonal.infrastructure.adapters.input.rest.data.response;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Transient;

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
public class MoveResponse {
    
    private MovePKRequest id;

    private String description;
    
    private Date dtMovement;
    
    private String user;
    
    private BigDecimal amount;
    
	private String cosif;

	private String produto;
}
