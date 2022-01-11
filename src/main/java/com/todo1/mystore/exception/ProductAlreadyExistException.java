package com.todo1.mystore.exception;

/**
 * @author cnaranjo
 *
 */
public class ProductAlreadyExistException extends Exception {

	private static final long serialVersionUID = 7739028443484154958L;

	public ProductAlreadyExistException(String errorMessage) {
		super(errorMessage);
	}
}


