package com.todo1.mystore.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.todo1.mystore.dto.ProductDTO;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTests {
	
	private IProductRepository productRepository = Mockito.mock(IProductRepository.class);

	@Test
	void GivenAProductToBeFound_WhenItExists_ThenItIsReturned() {
		var product = new ProductDTO();
		product.setName("Gaseosa XX");
				
		when(productRepository.findByName("\"Gaseosa XX\"")).thenReturn(Optional.of(product));
		
		Optional<ProductDTO> productFound = productRepository.findByName("\"Gaseosa XX\"");
		assertTrue(productFound.isPresent());
		assertEquals(productFound.get(), product);
	}
	
	@Test
	void GivenAProduct_WhenItDoesNotExists_ThenItIsSaved() {
		var product = new ProductDTO();
		product.setId(1L);
		product.setName("Gaseosa XX");
				
		when(productRepository.findByName("\"Gaseosa XX\"")).thenReturn(Optional.empty());
		when(productRepository.save(product)).thenReturn(product);
		
		Optional<ProductDTO> productFound = productRepository.findByName("\"Gaseosa XX\"");
		assertTrue(productFound.isEmpty());
		var savedProduct = productRepository.save(product);
		assertEquals(savedProduct, product);
	}

}
