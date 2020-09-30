/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.dao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.inventarioFacturacion.app.model.entity.PerfilEmpleado;

// TODO: Auto-generated Javadoc
/**
 * The Interface IPerfilEmpleado.
 */
@Repository
public interface IPerfilEmpleado  extends PagingAndSortingRepository<PerfilEmpleado, Long>{
	
	/**
	 * Desactivar empleado.
	 *
	 * @param estado the estado
	 * @param idEmpleado the id empleado
	 * @return the integer
	 */
	@Modifying 
	@Query("UPDATE PerfilEmpleado p set p.estado.id = :idEstado WHERE p.id = :idEmpleado")
	Integer DesactivarEmpleado( @Param("idEstado")Long estado, @Param("idEmpleado") Long idEmpleado);
	
	/**
	 * Find by nombre perfil.
	 *
	 * @param nombrePerfil the nombre perfil
	 * @return the perfil empleado
	 */
	 PerfilEmpleado findAllByNombrePerfil(String nombre);
	 
	 
	 
	
} 