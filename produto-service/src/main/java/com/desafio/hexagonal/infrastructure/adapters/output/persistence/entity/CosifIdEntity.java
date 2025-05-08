package com.desafio.hexagonal.infrastructure.adapters.output.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CosifIdEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5722258842635265930L;

	@Column(name = "COD_PRODUTO")
    private Long id;
    
    @Column(name = "COD_COSIF")
    private Long IdCosif;
}
