package com.todo1.mystore.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo1.mystore.service.IProductService;
import com.todo1.mystore.vo.ProductVO;

@WebMvcTest({ ProductController.class })
class ProductControllerTests {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IProductService productService;
	
	@Test
	void GivenAProduct_WhenItHasAnEmptyName_ThenReturnBadRequest() throws Exception {
		var product = new ProductVO();
		product.setName("");
		product.setInitialAmount(3);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/product")
				.content(objectMapper.writeValueAsString(product))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void GivenAProduct_WhenNameIsNull_ThenReturnBadRequest() throws Exception {
		var product = new ProductVO();
		product.setName(null);
		product.setInitialAmount(3);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/product")
				.content(objectMapper.writeValueAsString(product))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void GivenAProduct_WhenInitialAmountIsLowerThan1_ThenReturnBadRequest() throws Exception {
		var product = new ProductVO();
		product.setName("123");
		product.setInitialAmount(0);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/product")
				.content(objectMapper.writeValueAsString(product))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void GivenAProduct_WhenCorrectParameters_ThenReturnOk() throws Exception {
		var product = new ProductVO();
		product.setName("123");
		product.setInitialAmount(1);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/product")
				.content(objectMapper.writeValueAsString(product))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void GivenThereIsAProductFinding_WhenThereIsNotProductRegistered_ThenReturnAnEmptyList() throws Exception {
		List<ProductVO> productList = new ArrayList<>();
		
		when(productService.allProducts()).thenReturn(productList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/product"))
		.andExpect(status().isOk())
		.andExpect(content().string(objectMapper.writeValueAsString(productList)));
	}
	
	@Test
	void GivenThereIsAProductFinding_WhenThereIsProducstRegistered_ThenReturnAProductList() throws Exception {
		ProductVO product = new ProductVO();
		product.setName("123");
		product.setInitialAmount(1);
		List<ProductVO> productList = Arrays.asList(product);
		
		when(productService.allProducts()).thenReturn(productList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/product"))
		.andExpect(status().isOk())
		.andExpect(content().string(objectMapper.writeValueAsString(productList)));
	}

}
