/*
 * Fecha: 11-06-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.model.dto.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.inventarioFacturacion.app.model.entity.Categoria;
import com.inventarioFacturacion.app.model.entity.Empleado;

/**
 * The Class CategoriaConverter.
 */
@Component
public class CategoriaConverter {

	public List<Map<String, String>> listaCategorias(List<Categoria> lista) {
		// elementos
		Map<String, String> datos = new HashMap<>();
		List<Map<String, String>> response = new ArrayList<Map<String, String>>();

		if (lista.isEmpty() != true) {//Lista llena
                             
			for (Categoria categoria : lista) {
				
				datos.put("id", categoria.getId().toString());
				datos.put("nombre", categoria.getNombre());
				if(categoria.getDetalle() != null) {//si esta vacio se ingnora
					datos.put("detalle", categoria.getDetalle());
				}
				response.add(datos);
			}
			
		} else {//lista vacia
			datos.put("response", "null");
			response.add(datos);
		}
		return response;
	}

}
