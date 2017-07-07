package com.model;

import java.util.List;

public class UserException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private List<ModelError> error = null;
	
	public UserException(String msg,List<ModelError> error) {
		super(msg);
		this.error=error;
	}

	public UserException(String msg) {
		super(msg);
	}

	

}
