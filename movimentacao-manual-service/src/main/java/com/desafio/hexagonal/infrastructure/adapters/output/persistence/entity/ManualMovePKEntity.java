package com.desafio.hexagonal.infrastructure.adapters.output.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@EqualsAndHashCode
public class ManualMovePKEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1010815163502905621L;

	@Column(name = "DAT_MES")
    private Long month;

    @Column(name = "DAT_ANO")
    private Long year;

    @Column(name = "NUM_LANCAMENTO")
    private Long release;
    
	@Column(name = "COD_PRODUTO")
    private Long idProduct;
    
    @Column(name = "COD_COSIF")
    private Long idCosif;
    
    


}
