package com.inventarioFacturacion.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.inventarioFacturacion.app.model.entity.Categoria;
import com.inventarioFacturacion.app.model.entity.Empleado;
public interface ICategoria extends PagingAndSortingRepository<Categoria, Long> {
	/**
	 * Find by nombre.
	 *
	 * @param nombre the nombre
	 * @return the Categoria
	 */
	Categoria findByNombre(String nombre);
	/**
	 * El estado debe ser un nimero mayor a cero 1) activo, 0) inactivo
	 * */
	@Modifying 
	@Query("UPDATE Categoria categoria set categoria.estado = :estado WHERE categoria.id = :idCategoria")
	void DesactivarCategoria(@Param("estado") int estado, @Param("idCategoria") Long idCategoria);
	/**
	 * Listar todas las categorias cuyo id = 1 activo, 0 inactivo
	 * */
	@Query("SELECT categoria  FROM Categoria categoria WHERE categoria.estado = 1")
	List<Categoria> listar();

}
