/**
 * 
 */
package com.todo1.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo1.mystore.dto.KardexDTO;

/**
 * @author cnaranjo
 *
 */
@Repository
public interface IKardexRepository extends JpaRepository<KardexDTO, Long> {
	
}
