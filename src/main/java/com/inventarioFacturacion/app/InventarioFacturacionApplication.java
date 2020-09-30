/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// TODO: Auto-generated Javadoc
/**
 * The Class InventarioFacturacionApplication.
 */
@SpringBootApplication
public class InventarioFacturacionApplication extends SpringBootServletInitializer{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(InventarioFacturacionApplication.class, args);
	}
	
	/**
	 * Configure.
	 *
	 * @param application the application
	 * @return the spring application builder
	 */
	/*
	 * Paso 1: hablititar comentarios en pom.xml y quitar la libreria del tomcat embebido
	 * Paso 2 Agregar SpringApplicationBuilder configure
	 * paso 3: mvn clean package, crear el war
	 * Paso 4: subir el war
	 * 
	 * */

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InventarioFacturacionApplication.class);
    }
	

}
