/*
 * Fecha: 11-08-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.dao;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.inventarioFacturacion.app.model.entity.Entradas;

/**
 * The Interface IEntrada.
 */
public interface IEntrada extends PagingAndSortingRepository<Entradas, Long>{
	
	/**
	 * Filtro por fechas
	 *
	 * **/
	
	@Query("SELECT entradas FROM Entradas entradas WHERE entradas.createAt BETWEEN :inicio AND :fin")
	List<Entradas> findByDateInitAndDateEnd(@Param("inicio") Date inicio, @Param("fin") Date fin);

}
