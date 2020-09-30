/*
 * Fecha: 11-06-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventarioFacturacion.app.dao.ICategoria;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.entity.Categoria;
// TODO: Auto-generated Javadoc

/**
 * The Class CategoriaService.
 */
@Service
public class CategoriaService {

	/** The rol dao. */
	@Autowired
	private ICategoria icategoria;

	/**
	 * Save categoria.
	 *
	 * @param categoria the categoria
	 * @return true, if successful
	 */
	@Transactional // Save or update
	public boolean saveOrUpdateCategoria(Categoria categoria) {
		boolean response = true;
		Categoria categoriaValidate = new Categoria();
		categoriaValidate = icategoria.findByNombre(categoria.getNombre().toUpperCase());
		if (categoriaValidate != null) {// valida si existe registro
			// valida el update
			if (categoriaValidate.getNombre() == categoria.getNombre() && categoriaValidate.getId() == categoria.getId()
					|| categoriaValidate.getId() == categoria.getId()) {
				icategoria.save(categoria);
			} else {
				throw new ProcessingEntityException("El nombre de la categoria ya esta en uso");
			}
		} else {
			icategoria.save(categoria);// save
		}
		return response;
	}

	/**
	 * Disabe categoria.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	// Desactivar categoria
	@Transactional
	public boolean disabeCategoria(Long id) {
		icategoria.DesactivarCategoria(0, id);
		return true;
	}

	/**
	 * Listar categorias activas
	 * 
	 */
	@Transactional
	public List<Categoria> Listar() {
		return icategoria.listar();
	}
}
