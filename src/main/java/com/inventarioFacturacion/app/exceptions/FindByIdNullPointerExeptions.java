/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class FindByIdNullPointerExeptions.
 */
public class FindByIdNullPointerExeptions  extends RuntimeException {

	 /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5906029798625585977L;

	/**
	 * Instantiates a new find by id null pointer exeptions.
	 */
	public FindByIdNullPointerExeptions() {
		 super();
	 } 
	 
	 /**
 	 * Instantiates a new find by id null pointer exeptions.
 	 *
 	 * @param message the message
 	 */
 	public FindByIdNullPointerExeptions(String message) {
		 super(message);
	 } 
	 
	 /**
 	 * Instantiates a new find by id null pointer exeptions.
 	 *
 	 * @param id the id
 	 */
 	public FindByIdNullPointerExeptions(Long id) {
		 //%S paradigitos
		 super(String.format("Registro no encontrado: %d", id));
	 } 

}
