/**
 * 
 */
package com.todo1.mystore.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author cnaranjo
 *
 */
@Data
public class ProductVO {
	
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private int initialAmount;

}
