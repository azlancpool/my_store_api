package com.todo1.mystore.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.todo1.mystore.dto.ProductDTO;
import com.todo1.mystore.exception.ProductAlreadyExistException;
import com.todo1.mystore.repository.IProductRepository;
import com.todo1.mystore.vo.ProductVO;

@ExtendWith(MockitoExtension.class)
class ProductServiceTests {
	
	private IProductService productService = Mockito.mock(IProductService.class);
	
	private IProductRepository productRepository = Mockito.mock(IProductRepository.class);

	@Test
	void GivenAProduct_WhenItDoesNotExist_ThenItIsSaved() {
		var product = new ProductVO();
		product.setId(1L);
		product.setName("Gaseosa XX");
				
		try {
			productService.save(product);
		} catch (ProductAlreadyExistException e) {
			assertNull(e);
		}
	}
	
	@Test
	void GivenAProduct_WhenItExists_ThenAProductAlreadyExistExceptionIsThrown() {
		var product = new ProductVO();
		product.setId(1L);
		product.setName("Gaseosa XX");
		
		var productFound = new ProductDTO();
		productFound.setId(1L);
		productFound.setName("Gaseosa XX");
		
		when(productRepository.findByName("Gaseosa XX")).thenReturn(Optional.of(productFound));
				
		try {
			productService.save(product);
		} catch (ProductAlreadyExistException e) {
			assertNotNull(e);
		}
	}

}
