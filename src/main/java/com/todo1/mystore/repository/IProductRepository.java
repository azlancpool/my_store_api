/**
 * 
 */
package com.todo1.mystore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo1.mystore.dto.ProductDTO;

/**
 * @author cnaranjo
 *
 */
@Repository
public interface IProductRepository extends JpaRepository<ProductDTO, Long> {
	
	Optional<ProductDTO> findByName(String name);

}
