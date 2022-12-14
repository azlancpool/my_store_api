/**
 * 
 */
package com.todo1.mystore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo1.mystore.exception.ProductAlreadyExistException;
import com.todo1.mystore.service.IProductService;
import com.todo1.mystore.vo.ProductVO;

/**
 * @author cnaranjo
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService productService;

	@PostMapping
	public ResponseEntity<Boolean> newProduct(@RequestBody @Valid ProductVO product) {
		try {
			productService.save(product);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (ProductAlreadyExistException e) {
			return new ResponseEntity<>(true, HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<ProductVO>> allProducts() {
		return new ResponseEntity<>(productService.allProducts(), HttpStatus.OK);
	}
}
