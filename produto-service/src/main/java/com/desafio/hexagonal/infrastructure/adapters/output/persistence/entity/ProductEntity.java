package com.desafio.hexagonal.infrastructure.adapters.output.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "PRODUTO")
public class ProductEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8935030855730408568L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_PRODUTO")
    private Long id;

    @Column(name = "DES_PRODUTO")
    private String description;

    @Column(name = "STA_STATUS")
    private String status;
    
    @OneToMany(mappedBy = "produto")
    private List<ProductCosifEntity> productCosifList;

}
