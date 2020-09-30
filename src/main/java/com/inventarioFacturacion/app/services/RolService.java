/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inventarioFacturacion.app.dao.IRol;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.entity.Rol;

// TODO: Auto-generated Javadoc
/**
 * The Class RolService.
 */
@Service
public class RolService {

	/** The rol dao. */
	@Autowired
	private IRol rolDao;

	/** 
	 * Save rol.
	 *
	 * @param rol the rol
	 * @return true, if successful
	 */
	@Transactional
	public boolean saveRol(Rol rol) {
		boolean response = true;		
           Rol rolValidate = new Rol();
           rolValidate = rolDao.findByNombre(rol.getNombre().toUpperCase());
			if (rolValidate!=null) {//valida si existe registro
				//valida el update
				  if(rolValidate.getNombre() == rol.getNombre() && rolValidate.getId() == rol.getId() ||  rolValidate.getId() == rol.getId()) {
					  rolDao.save(rol);//update  
				  }else {
					  throw new ProcessingEntityException("El nombre del Rol ya esta en uso");
				  }
				  
			}else {
				rolDao.save(rol);//save
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
	public boolean deleteRol(Long id) {
		boolean response = true;
		try {
			rolDao.deleteById(id);
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
	public List<Rol> listRol() {
		List<Rol> list = null;
		try {
			list = (List<Rol>) rolDao.findAll();
		} catch (Exception e) {
			System.out.print(e);
			throw new ProcessingEntityException("Error al listar Roles");
		}
		return list;
	}

	/**
	 * List rol exepto inactivo.
	 *
	 * @return the list
	 */
	@Transactional
	public List<Rol> listRolExeptoInactivo() {
		List<Rol> list = null;
		try {
			list = (List<Rol>) rolDao.findAllExeptedInactivo();
		} catch (Exception e) {
			System.out.print(e);
			throw new ProcessingEntityException("Error al listar Roles");
		}
		return list;
	}
	
}
