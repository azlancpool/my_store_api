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

import com.todo1.mystore.dto.KardexDTO;
import com.todo1.mystore.dto.ProductDTO;
import com.todo1.mystore.exception.ProductAlreadyExistException;
import com.todo1.mystore.repository.IKardexRepository;
import com.todo1.mystore.repository.IProductRepository;
import com.todo1.mystore.service.IProductService;
import com.todo1.mystore.vo.ETransactionType;
import com.todo1.mystore.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cnaranjo
 *
 */
@Service
@Slf4j
@Transactional
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private IKardexRepository kardexRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void save(ProductVO product) throws ProductAlreadyExistException {
		var op = productRepository.findByName(product.getName());
		if (op.isPresent()) {
			throw new ProductAlreadyExistException("Product with name " + product.getName() + " already exist");
		}

		var savedProduct = productRepository.save(modelMapper.map(product, ProductDTO.class));
		
		// TODO: Handle in a proper way the kardex handling
		var kardex = new KardexDTO(savedProduct, ETransactionType.INIT_TRANSACTION, "Init register", product.getInitialAmount(),product.getUnitValue());
		
		kardexRepository.save(kardex);
		
		var currentKardexInfo = new KardexDTO(savedProduct, ETransactionType.CURRENT, "Updating current vlaue", product.getInitialAmount(),product.getUnitValue());
		kardexRepository.save(currentKardexInfo);
		
		var resultKardexLIst = kardexRepository.findAll();
		log.info(">>>>> kardex saved {}", resultKardexLIst);
	}

	@Override
	public List<ProductVO> allProducts() {
		return productRepository.findAll().stream().map(p -> modelMapper.map(p, ProductVO.class)).collect(Collectors.toList());
	}

}
