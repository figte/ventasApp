/*
 * Fecha: 11-07-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventarioFacturacion.app.dao.ICategoria;
import com.inventarioFacturacion.app.dao.IEntrada;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.entity.Categoria;
import com.inventarioFacturacion.app.model.entity.Entradas;

/**
 * The Class EntradaService, las entradas no se pueden eliminar, solo actualizar 
 */
@Service
public class EntradaService {
	
	@Autowired
	private IEntrada ientrada;
	
	@Transactional // Save or update
	public boolean saveOrUpdateentradas(Entradas entrada) {
		boolean response = true;
		try {
			if(entrada.getId() != null){
				/**
				 * validación de entrada y manipulación de stock
				 * 
				 * **/
				ientrada.save(entrada);//update
			}else{
				/**
				 * validación de entrada y manipulación de stock
				 * 
				 * **/
				ientrada.save(entrada);// save
			}
			
		} catch (Exception e) {
			throw new ProcessingEntityException("Error al guardar entidad"+e.getMessage());
		}

		return response;
	}
	
	/**
	 * Listar entradas activas.
	 *
	 * @return the list
	 */
	@Transactional
	public List<Entradas> Listar(String inicio, String fin) {
		//System.out.println(inicio); System.out.println(fin);
		try {
			List<Entradas>  entradas = ientrada.findByDateInitAndDateEnd(new SimpleDateFormat("yyyy-MM-dd").parse(inicio),
					new SimpleDateFormat("yyyy-MM-dd").parse(fin));
			
			return entradas;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	

}
