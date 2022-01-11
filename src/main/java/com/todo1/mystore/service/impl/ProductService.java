/**
 * 
 */
package com.todo1.mystore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.mystore.dto.ProductDTO;
import com.todo1.mystore.exception.ProductAlreadyExistException;
import com.todo1.mystore.repository.IProductRepository;
import com.todo1.mystore.service.IProductService;
import com.todo1.mystore.vo.ProductVO;

/**
 * @author cnaranjo
 *
 */
@Service
@Transactional
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void save(ProductVO product) throws ProductAlreadyExistException {
		var op = productRepository.findByName(product.getName());
		if (op.isPresent()) {
			throw new ProductAlreadyExistException("Product with name " + product.getName() + " already exist");
		}

		productRepository.save(modelMapper.map(product, ProductDTO.class));
	}

	@Override
	public List<ProductVO> allProducts() {
		return productRepository.findAll().stream().map(p -> modelMapper.map(p, ProductVO.class)).collect(Collectors.toList());
	}

}
