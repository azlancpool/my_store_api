/**
 * 
 */
package com.todo1.mystore.dto;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;

/**
 * @author cnaranjo
 *
 */
@Entity
@Data
public class KardexDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	@SequenceGenerator(name = "sequence", sequenceName = "SEQ_KARDEX", allocationSize = 1)
	private Long id;
		
	@ManyToOne
	private ProductDTO product;

	private String transactionType;
	
	private String detail;
	
	private int amount;
	
	private BigDecimal unitValue;
	
	private BigDecimal totalValue;
}
