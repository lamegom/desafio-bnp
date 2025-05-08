package com.desafio.hexagonal.infrastructure.adapters.input.rest.data.response;

import com.desafio.hexagonal.domain.model.CosifId;

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
public class ProductCosifResponse {
    
	private CosifId pk;

    private String classifier;
    
    private String status;
}
