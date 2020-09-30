/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.components;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
// TODO: Auto-generated Javadoc

/**
 * The Class ResponseJson.
 */
@Component
public class ResponseJson {
	
	/** The json response. */
	@Autowired
	private ResponseJson jsonResponse;
	
	/** The validated. */
	private boolean validated;
	
	/** The error messages. */
	private Map<String, String> errorMessages;


	/**
	 * Checks if is validated.
	 *
	 * @return true, if is validated
	 */
	// Metodos
	public boolean isValidated() {
		return validated;
	}

	/**
	 * Sets the validated.
	 *
	 * @param validated the new validated
	 */
	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	/**
	 * Sets the error messages.
	 *
	 * @param errorMessages the error messages
	 */
	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	/**
	 * Gets the error messages.
	 *
	 * @return the error messages
	 */
	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	
	/**
	 * Validate json.
	 *
	 * @param result the result
	 * @return the response json
	 */
	public ResponseJson validateJson(BindingResult result) {
		
		if (result.hasErrors()) {
			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

			jsonResponse.setValidated(false);
			jsonResponse.setErrorMessages(errors);
		} else {

			jsonResponse.setValidated(true);
		}
		return jsonResponse;

	}
}
