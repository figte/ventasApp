/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.model.dto.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.entity.Puesto;
// TODO: Auto-generated Javadoc

/**
 * Componente encargado de convertir manualmente a formato Json.
 *
 * @Jaime_Ramírez 19-9-2019
 */

@Component
public class PuestosConverter {
	
	/**
	 * Lista rol.
	 *
	 * @param lista the lista
	 * @return the list
	 */
	public List<Map<String, String>> listaPuestos(List<Puesto> lista) {

		List<Map<String, String>> listOfMaps = new ArrayList<Map<String, String>>();

		Map<String, String> map = null;
		try {
			for (Puesto puesto : lista) {
				map = new HashMap<String, String>();

				map.put("nombre", puesto.getNombre());
				map.put("detalle", puesto.getDetalle());
				map.put("correlativo", Long.toString(puesto.getId()));
				map.put("editar",
						"<button type='button' class='btn btn-outline-success openEdit'><i class='fa fa-edit'></i> </button>");
				map.put("eliminar",
						"<button type='button' class='btn btn-outline-danger delete'><i class='fa fa-minus'></i> </button>");
				listOfMaps.add(map);
			}

		} catch (Exception e) {
			throw new ProcessingEntityException("Error al procesar el Rol");
		}
		return listOfMaps;
	}
}
