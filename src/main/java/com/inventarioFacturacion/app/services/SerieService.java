package com.inventarioFacturacion.app.services;

import java.util.List;

import com.inventarioFacturacion.app.dao.ISeries;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.entity.Serie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SerieService
 */

 @Service
public class SerieService {

    @Autowired
    ISeries serieDao;

   	/**
	 * Save rol.
	 *
	 * @param rol the rol
	 * @return true, if successful
	 */
	@Transactional
	public boolean saveSerie(Serie serie) {
		boolean response = true;
		//System.out.println("Error:" +serie.toString());

		try {

			serie=serieDao.save(serie);// save
  
			serie=serieDao.findById(serie.getId()).get();
			//obteniendo todas las series para cambiar estado
			List<Serie> l=(List<Serie>) serieDao.findAll();

		
			//cambiando las demas series a estado inactivo
			for (Serie item : l) {
				
				if(serie.getEstado().equals("activo")){ //si la serie a modificar o guardar tiene estado activo
					if (serie.getId()!=item.getId()) { // las series diferente a la guardar o actualizar cambiaran de estado
						item.setEstado("inactivo");
						serieDao.save(item);  
					}
					
				}

				
			}

		} catch (Exception e) {
			System.out.println("Error:" +e);
			throw new ProcessingEntityException("El nombre de la Serie ya esta en uso"+e.getMessage());
			
		}
		return response;
	}

	/**
	 * Delete rol.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	@Transactional
	public boolean deleteSerie(Long id) {
		boolean response = true;
		try {
			serieDao.deleteById(id);
		} catch (Exception e) {
			response = false;
			throw new ProcessingEntityException("Error al eliminar el Rol");
		}
		return response;
	}

	/**
	 * List rol.
	 *
	 * @return the list
	 */
	// todos los roles
	@Transactional
	public List<Serie> listSeries() {
		List<Serie> list = null;
		try {
			list = (List<Serie>) serieDao.findAll();
		} catch (Exception e) {
			System.out.print(e);
			throw new ProcessingEntityException("Error al listar Series");
		}
		return list;
	}

	
	
    
}