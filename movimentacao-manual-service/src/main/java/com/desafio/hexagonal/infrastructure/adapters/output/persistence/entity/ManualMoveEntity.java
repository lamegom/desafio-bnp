package com.desafio.hexagonal.infrastructure.adapters.output.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "MOVIMENTO_MANUAL")
public class ManualMoveEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7255481593438750883L;

	@EmbeddedId 
    private ManualMovePKEntity id;;

    @Column(name = "DES_DESCRICAO")
    private String description;
    
    @Column(name = "DAT_MOVIMENTO")
    private Date dtMovement;
    
	@Column(name = "COD_USUARIO")
    private String user;
    
	@Column(name = "VAL_VALOR")
    private BigDecimal amount;

}
