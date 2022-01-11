/**
 * 
 */
package com.todo1.mystore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.mystore.dto.ProductDTO;
import com.todo1.mystore.exception.ProductAlreadyExistException;
import com.todo1.mystore.repository.IProductRepository;
import com.todo1.mystore.service.IProductService;

/**
 * @author cnaranjo
 *
 */
@Service
@Transactional
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Override
	public void save(ProductDTO product) throws ProductAlreadyExistException {
		var op = productRepository.findByName(product.getName());
		if (op.isPresent()) {
			throw new ProductAlreadyExistException("Product with name " + product.getName() + " already exist");
		}

		productRepository.save(product);
	}

}
