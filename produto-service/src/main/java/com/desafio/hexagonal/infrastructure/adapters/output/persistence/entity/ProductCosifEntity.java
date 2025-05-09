package com.desafio.hexagonal.infrastructure.adapters.output.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "PRODUTO_COSIF")
public class ProductCosifEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5389650889109757926L;

	@EmbeddedId 
    private CosifIdEntity pk;

    @Column(name = "COD_CLASSIFICACAO")
    private String classifier;

    @Column(name = "STA_STATUS")
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "COD_PRODUTO", insertable=false,updatable=false) 
    private ProductEntity produto;
    
    

}
