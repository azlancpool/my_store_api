/**
 * 
 */
package com.todo1.mystore.service;

import com.todo1.mystore.dto.ProductDTO;
import com.todo1.mystore.exception.ProductAlreadyExistException;

/**
 * @author cnaranjo
 *
 */
public interface IProductService {

	void save(ProductDTO product) throws ProductAlreadyExistException;

}
