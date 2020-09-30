/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.inventarioFacturacion.app.dao.ISucursal;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.entity.Sucursal;

// TODO: Auto-generated Javadoc
/**
 * The Class SucursalService.
 */
@Repository
public class SucursalService {

	/** The isucursal. */
	@Autowired
	private ISucursal isucursal;

	/**
	 * Save sucursal.
	 *
	 * @param sucursal the sucursal
	 * @return true, if successful
	 */
	@Transactional
	public boolean saveSucursal(Sucursal sucursal) {
		boolean response = true;
	//	System.out.println("Error:" +sucursal.toString());

		try {
			isucursal.save(sucursal);// save
		} catch (Exception e) {
			System.out.println("Error:" +e);
			throw new ProcessingEntityException("El nombre de la Sucursal ya esta en uso");
			
		}
		return response;
	}

	/**
	 * Delete sucursal.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	@Transactional
	public boolean deleteSucursal(Long id) {

		boolean response = true;
		try {
			isucursal.deleteById(id);// delete
		} catch (Exception e) {
			response = false;
			throw new ProcessingEntityException("La sucursal cuenta con registros, para eliminarla primero elimine todos sus dependencias");
		}
		return response;
	}

	/**
	 * List sucursal.
	 *
	 * @return the list
	 */
	@Transactional
	public List<Sucursal> listSucursal() {
		List<Sucursal> list = null;
		try {
			list = (List<Sucursal>) isucursal.findAll();
		} catch (Exception e) {

			System.out.print(e);
		}
		return list;
	}
}
