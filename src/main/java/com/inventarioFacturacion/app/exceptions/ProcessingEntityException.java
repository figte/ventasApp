/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class ProsessingEntityException.
 */
public class ProcessingEntityException extends RuntimeException{
     
     /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7029370427402601982L;
	
	/**
	 * Instantiates a new prosessing entity exception.
	 */
	public ProcessingEntityException() {
		 super();
	 } 
	 
	 /**
 	 * Instantiates a new prosessing entity exception.
 	 *
 	 * @param message the message
 	 */
 	public ProcessingEntityException(String message) {
		 super(message);
	 } 
	 
}
