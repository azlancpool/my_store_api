/**
 * 
 */
package com.todo1.mystore.vo;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author cnaranjo
 *
 */
@Data
public class ProductVO {
	
	private Long id;

	@NotEmpty(message = "The product name must not be empty.")
	private String name;

	@NotNull
	@Min(value = 1, message = "The initial amount of a product must be upper than 1.")
	private Integer initialAmount;
	
	@NotNull
	@Min(value = 1, message = "The unit value of a product must be upper than 1.")
	private BigDecimal unitValue;

}
