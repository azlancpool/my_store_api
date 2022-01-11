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

import com.todo1.mystore.vo.ETransactionType;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cnaranjo
 *
 */
@Entity
@Data
@NoArgsConstructor
public class KardexDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	@SequenceGenerator(name = "sequence", sequenceName = "SEQ_KARDEX", allocationSize = 1)
	private Long id;
		
	@ManyToOne
	private ProductDTO product;

	private ETransactionType transactionType;
	
	private String detail;
	
	private int amount;
	
	private BigDecimal unitValue;
	
	private BigDecimal totalValue;

	public KardexDTO(ProductDTO product, ETransactionType transactionType, String detail, int amount,
			BigDecimal unitValue) {
		super();
		this.product = product;
		this.transactionType = transactionType;
		this.detail = detail;
		this.amount = amount;
		this.unitValue = unitValue;
		this.totalValue = unitValue.multiply(new BigDecimal(amount));
	}
	
	
}
