/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inventarioFacturacion.app.dao.IPuesto;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.entity.Puesto;

// TODO: Auto-generated Javadoc
/**
 * The Class RolService.
 */
@Service
public class PuestoService {

	/** The rol dao. */
	@Autowired
	private IPuesto ipuesto;

	/**
	 * Save rol.
	 *
	 * @param rol the rol
	 * @return true, if successful
	 */
	@Transactional
	public boolean savePuesto(Puesto puesto) {
		boolean response = true;		
           Puesto puestoValidate = new Puesto();
           puestoValidate = ipuesto.findByNombre(puesto.getNombre().toUpperCase());
			if (puestoValidate!=null) {//valida si existe registro
				//valida el update
				  if(puestoValidate.getNombre() == puesto.getNombre() && puestoValidate.getId() == puesto.getId() ||  puestoValidate.getId() == puesto.getId()) {
					  ipuesto.save(puesto);//update  
				  }else {
					  throw new ProcessingEntityException("El nombre ya esta en uso");
				  }
				  
			}else {
				ipuesto.save(puesto);//save
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
	public boolean deletePuesto(Long id) {
		boolean response = true;
		try {
			ipuesto.deleteById(id);
		} catch (Exception e) {
			response = false;
			System.out.print("error: "+e);
			throw new ProcessingEntityException("Error al eliminar el registro");
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
	public List<Puesto> listPuesto() {
		List<Puesto> list = null;
		try {
			list = (List<Puesto>) ipuesto.findAll();
		} catch (Exception e) {
			System.out.print("error: "+e);
			throw new ProcessingEntityException("Error al listar los registros");
		}
		return list;
	}

	
	
}
