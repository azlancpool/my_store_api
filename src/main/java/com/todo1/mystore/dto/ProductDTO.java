/**
 * 
 */
package com.todo1.mystore.dto;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

/**
 * @author cnaranjo
 *
 */
@Entity
@Data
public class ProductDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	@SequenceGenerator(name = "sequence", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="product")
    private Set<KardexDTO> kardex;

}
