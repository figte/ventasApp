package com.inventarioFacturacion.app.dao;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.inventarioFacturacion.app.model.entity.Producto;
public interface IProducto extends PagingAndSortingRepository<Producto, String> {
	
	/**
	 * Listar todas las categorias cuyo id = 1 activo, 0 inactivo
	 * */
	@Query("SELECT producto  FROM Producto producto WHERE producto.estado = 1 and producto.categoria.id = :categoriaId")
	List<Producto> listarByCategoria(@Param("categoriaId") Long categoriaId);

}
