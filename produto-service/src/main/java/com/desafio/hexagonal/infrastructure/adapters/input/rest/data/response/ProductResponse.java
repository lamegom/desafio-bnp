package com.desafio.hexagonal.infrastructure.adapters.input.rest.data.response;

import java.util.List;

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
public class ProductResponse {
    
    private Long id;

    private String description;
    
    private String status;
    
    private List<ProductCosifResponse> productCosifList;

   

}
