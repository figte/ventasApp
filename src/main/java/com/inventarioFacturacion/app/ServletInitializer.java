/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
// TODO: Auto-generated Javadoc
/**
 * The Class ServletInitializer.
 */

public class ServletInitializer extends SpringBootServletInitializer {
	@Autowired
	@SuppressAjWarnings
	private ApplicationContext applicationContext;
	/**
	 * Configure.
	 *
	 * @param application the application
	 * @return the spring application builder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(InventarioFacturacionApplication.class);
	}
	
}
