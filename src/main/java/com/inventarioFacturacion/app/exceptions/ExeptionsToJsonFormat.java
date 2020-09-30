/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.exceptions;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class ExeptionsToJsonFormat.
 */
public class ExeptionsToJsonFormat {
	  
	    /** The timestamp. */
    	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	    private LocalDateTime timestamp;
	    
    	/** The status. */
    	private int status;
	    
    	/** The error. */
    	private String error;
	    
    	/** The message. */
    	private String message;
	    
	    
		/**
		 * Gets the timestamp.
		 *
		 * @return the timestamp
		 */
		public LocalDateTime getTimestamp() {
			return timestamp;
		}
		
		/**
		 * Sets the timestamp.
		 *
		 * @param timestamp the new timestamp
		 */
		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
		
		/**
		 * Gets the status.
		 *
		 * @return the status
		 */
		public int getStatus() {
			return status;
		}
		
		/**
		 * Sets the status.
		 *
		 * @param status the new status
		 */
		public void setStatus(int status) {
			this.status = status;
		}
		
		/**
		 * Gets the error.
		 *
		 * @return the error
		 */
		public String getError() {
			return error;
		}
		
		/**
		 * Sets the error.
		 *
		 * @param error the new error
		 */
		public void setError(String error) {
			this.error = error;
		}

		/**
		 * Gets the message.
		 *
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * Sets the message.
		 *
		 * @param message the new message
		 */
		public void setMessage(String message) {
			this.message = message;
		}
	    
	    
	    
}
