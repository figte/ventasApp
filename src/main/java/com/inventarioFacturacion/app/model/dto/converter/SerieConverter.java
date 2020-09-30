package com.inventarioFacturacion.app.model.dto.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.dto.SerieDto;
import com.inventarioFacturacion.app.model.entity.Serie;

import org.springframework.stereotype.Component;

/**
 * SerieConverter
 */
@Component
public class SerieConverter {

    
	/**
	 * Lista rol.
	 *
	 * @param lista the lista
	 * @return the list
	 */
	public List<Map<String, String>> listaSeries(List<Serie> lista) {

		List<Map<String, String>> listOfMaps = new ArrayList<Map<String, String>>();

		Map<String, String> map = null;
		try {
			for (Serie serie : lista) {
				map = new HashMap<String, String>();

				map.put("correlativo", Long.toString(serie.getId()));
				map.put("serie", serie.getSerie());
                map.put("CorrelativoActual",serie.getCorrelativoActual().toString());
                map.put("inicio",serie.getInicio().toString());
				map.put("fin",serie.getFin().toString());
				map.put("estado",serie.getEstado());		
				map.put("editar",
						"<button type='button' class='btn btn-outline-success openEdit'><i class='fa fa-edit'></i> </button>");
				map.put("eliminar",
						"<button type='button' class='btn btn-outline-danger openDelete'><i class='fa fa-minus'></i> </button>");
				listOfMaps.add(map);
			}

		} catch (Exception e) {
			throw new ProcessingEntityException("Error al procesar la serie");
		}
		return listOfMaps;
	}

	public Serie getSerie(SerieDto serieDto){
		Serie s=new Serie();
			try {
				s.setId(serieDto.getId());
				s.setSerie(serieDto.getSerie());
				s.setCorrelativoActual(serieDto.getCorrelativoActual());
				s.setInicio(serieDto.getInicio());
				s.setFin(serieDto.getFin());
				s.setEstado(serieDto.getEstado());
				
				return s;
			} catch (Exception e) {
				//TODO: handle exception
				return null;
			}
	}
}