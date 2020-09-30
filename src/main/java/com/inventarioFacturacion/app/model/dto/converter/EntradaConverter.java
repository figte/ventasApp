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
import com.inventarioFacturacion.app.model.entity.Entradas;
import com.inventarioFacturacion.app.model.entity.Sucursal;
// TODO: Auto-generated Javadoc

/**
 * Componente encargado de convertir manualmente a formato Json.
 *
 * @Jaime_Ramírez 19-9-2019
 */

@Component
public class EntradaConverter {
	
	/**
	 * Lista sucursal.
	 *
	 * @param lista the lista
	 * @return the list
	 */
	
	public List<Map<String, String>> listaEntradas(List<Entradas> lista) {

		List<Map<String, String>> listOfMaps = new ArrayList<>();

		Map<String, String> map = null;
		try {
			for (Entradas element : lista) {
				map = new HashMap<String, String>();
				map.put("correlativo", Long.toString(element.getId()));
				map.put("codigo_producto", element.getProducto().getCodigo());
				map.put("producto", element.getProducto().getNombre());
				map.put("cantidad", element.getCantidad().toString());
				map.put("unidad_medida", element.getProducto().getUnidadDeMedida());
				map.put("documento", element.getDocumento());
				map.put("codigo", element.getCodigo());
				map.put("fecha_ingreso", element.getCreateAt().toString());
				if(element.getDatalle() !=null) {
					map.put("detalle", element.getDatalle());	
				}else {
					map.put("detalle", "");
				}
				map.put("tipo_entrada", element.getTipoDeEntrada());
				
				listOfMaps.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ProcessingEntityException("Error al procesar la entrada");
		}

		return listOfMaps;
	}
}
