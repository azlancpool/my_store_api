/**
 * 
 */
package com.todo1.mystore.service;

import java.util.List;

import com.todo1.mystore.exception.ProductAlreadyExistException;
import com.todo1.mystore.vo.ProductVO;

/**
 * @author cnaranjo
 *
 */
public interface IProductService {

	void save(ProductVO product) throws ProductAlreadyExistException;
	
	List<ProductVO> allProducts();

}
