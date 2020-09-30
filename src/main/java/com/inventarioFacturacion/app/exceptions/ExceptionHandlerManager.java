// /*
//  * Fecha: 09-23-2019
//  * @Jaime_Ramirez
//  */
// package com.inventarioFacturacion.app.exceptions;

// import java.time.LocalDateTime;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.context.request.WebRequest;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
// // TODO: Auto-generated Javadoc

// /**
//  * Controlador encargado de manegar las ecepciones personalizados
//  * respecto los diferentes procesos del sistema.
//  *
//  * @Jaime_Ramírez 19-9-2019
//  */
// @ControllerAdvice
// public class ExceptionHandlerManager extends ResponseEntityExceptionHandler {
	

// 	/**
// 	 * Exception empleado.
// 	 *
// 	 * @param ex the ex
// 	 * @param request the request
// 	 * @return the response entity
// 	 */
// 	@ExceptionHandler(FindByIdNullPointerExeptions.class)
// 	public ResponseEntity<Object> exceptionEmpleado(Exception ex, WebRequest request) {
// 		return new ResponseEntity<>(setErrors(ex,HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
// 	}
	
	
// 	/**
// 	 * Exception entity.
// 	 *
// 	 * @param ex the ex
// 	 * @param request the request
// 	 * @return the response entity
// 	 */
// 	@ExceptionHandler({ProcessingEntityException.class,RegisterDuplicateException.class})
// 	public ResponseEntity<Object> exceptionEntity(Exception ex, WebRequest request) {
// 		return new ResponseEntity<>(setErrors(ex,HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
// 	}
	
// 	/**
// 	 * Exception rol.
// 	 *
// 	 * @param exception the exception
// 	 * @return the response entity
// 	 */
// 	@ExceptionHandler(RolesNotFoundExceptions.class)
// 	public ResponseEntity<Object> exceptionRol(RolesNotFoundExceptions exception) {
// 		return new ResponseEntity<>("roles_not_found", HttpStatus.NOT_FOUND);
// 	}

// 	/**
// 	 * Sets the errors.
// 	 *
// 	 * @param ex the ex
// 	 * @param status the status
// 	 * @return the exeptions to json format
// 	 */
// 	//Construlle el archivo Json de errors
// 	private ExeptionsToJsonFormat setErrors(Exception ex, HttpStatus status) {
// 		ExeptionsToJsonFormat errors = new ExeptionsToJsonFormat();
// 		errors.setTimestamp(LocalDateTime.now());
// 		errors.setError(status.name());
// 		errors.setStatus(status.value());
// 		errors.setMessage(ex.getMessage());
// 		return errors;

// 	}
// }
