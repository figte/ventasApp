/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.dao;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.inventarioFacturacion.app.model.entity.PerfilEmpleado;
import com.inventarioFacturacion.app.model.entity.RolPerfil;

/**
 * The Interface IRolPerfil.
 */
@Repository
public interface IRolPerfil extends PagingAndSortingRepository<RolPerfil, Long> {
	@Query("select perfiles from RolPerfil perfiles where perfiles.perfilEmpleado.id = :idPerfil")
	Collection<RolPerfil> findByEmpleadoId(@Param("idPerfil") long idPerfil);
	
	//@Query("delete from RolPerfil where perfilEmpleado.id = :idPerfil")
	void deleteByPerfilEmpleado(/*@Param("idPerfil")*/ PerfilEmpleado idPerfil);
	
	
	
} 