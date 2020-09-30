/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterDuplicateException.
 */
public class RegisterDuplicateException extends RuntimeException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3391636622999414983L;
	
	/**
	 * Instantiates a new register duplicate exception.
	 */
	public RegisterDuplicateException() {
		 super();
	 } 
	 
 	/**
 	 * Instantiates a new register duplicate exception.
 	 *
 	 * @param message the message
 	 */
 	public RegisterDuplicateException(String message) {
		 super(message);
	 } 
}
