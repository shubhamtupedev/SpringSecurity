package com.example.springsecurity.Exception;

public class ServiceException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
        super("Something Went Wrong! please contact Administrator");
    }
}
