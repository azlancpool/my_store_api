/**
 * 
 */
package com.todo1.mystore.vo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author cnaranjo
 *
 */
@Data
public class KardexVO {
	private Long id;
		
	private ProductVO product;

	private ETransactionType transactionType;
	
	private String detail;
	
	private int amount;
	
	private BigDecimal unitValue;
	
	private BigDecimal totalValue;
}
