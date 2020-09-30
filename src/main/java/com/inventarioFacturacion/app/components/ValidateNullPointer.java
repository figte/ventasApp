/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.components;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidateNullPointer.
 */
@Component
public class ValidateNullPointer {
	
	/**
	 * Validate to string.
	 *
	 * @param string the string
	 * @return the string
	 */
	public String validateToString(String string) {
		
		if(string == null) {
			string = "";
		}else if(string != "") {
			 string=string.trim();
			 if(string != "") { 
			 //No hacer ninguna accion
			 }else {
				 string = "";
			 }
			 //No hacer ninguna accion
		}else {
			string = "";
		}
		return string;
	}
	
	
	/**
	 * Validate to long.
	 *
	 * @param dato the dato
	 * @return the long
	 */
	public  long validateToLong(Long dato) {
		long response = 0l;
		   //Regresa un ""    
		if(dato != null) {
			response = dato;
		}
		return response;
	}
}