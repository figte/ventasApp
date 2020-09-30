/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.inventarioFacturacion.app.model.entity.Empleado;

// TODO: Auto-generated Javadoc
/**
 * The Interface IEmpleado.
 */
@Repository
public interface IEmpleado extends PagingAndSortingRepository<Empleado, Long> {
	
	/**
	 * Find all.
	 *
	 * @param estado the estado
	 * @return the list
	 */
	// Todos los empleados activos
	@Query("select empleado0 from Empleado empleado0 "
			+ "inner join PerfilEmpleado perfilempl1_ on empleado0.perfil = perfilempl1_.id "
			+ "inner join Estado estado_ on perfilempl1_.estado = estado_.id" + " where estado_.nombre= :estado")
	List<Empleado> findAll(@Param("estado") String estado);

	/**
	 * Find by id.
	 *
	 * @param estado the estado
	 * @param idEmpleado the id empleado
	 * @return the list
	 */
	
	/**
	 * Find by cargo and sucursal.
	 *
	 * @param idSucursal the id sucursal
	 * @param estado the estado
	 * @param cargo the cargo
	 * @return the list
	 */
	// Filtro de empleados por cargo, sucursal id y estado
	@Query("select empleado0 from Empleado empleado0 "
			+ "inner join PerfilEmpleado perfilempl1_ on empleado0.perfil = perfilempl1_.id "
			+ "inner join Sucursal sucursal_ on empleado0.sucursal = sucursal_.id "
			+ "inner join Estado estado_ on perfilempl1_.estado = estado_.id"
			+ " where sucursal_.id = :idSucursal and estado_.nombre= :estado and empleado0.cargo = :cargo")
	List<Empleado> findByCargoAndSucursal(@Param("idSucursal") Long idSucursal, @Param("estado") String estado,
			@Param("cargo") String cargo);

	/**
	 * Find by sucursal.
	 *
	 * @param idSucursal the id sucursal
	 * @param estado the estado
	 * @return the list
	 */
	// Buscar por sucursal y estado
	@Query("select empleado0 from Empleado empleado0 "
			+ "inner join PerfilEmpleado perfilempl1_ on empleado0.perfil = perfilempl1_.id "
			+ "inner join Sucursal sucursal_ on empleado0.sucursal = sucursal_.id "
			+ "inner join Estado estado_ on perfilempl1_.estado = estado_.id"
			+ " where sucursal_.id = :idSucursal and estado_.nombre= :estado")
	List<Empleado> findBySucursal(@Param("idSucursal") Long idSucursal, @Param("estado") String estado);

	/**
	 * Perfil by cargo and estado.
	 *
	 * @param cargo the cargo
	 * @param estado the estado
	 * @return the list
	 */
	// Busqueda por cargo y estado
	@Query("select empleados0 from Empleado empleados0 "
			+ "inner join PerfilEmpleado perfilempl1_ on empleados0.perfil = perfilempl1_.id "
			+ "inner join Estado estado_ on perfilempl1_.estado = estado_.id"
			+ " where  empleados0.cargo = :cargo and estado_.nombre= :estado")
	List<Empleado> perfilByCargoAndEstado(@Param("cargo") String cargo, @Param("estado") String estado);
	
	
	
}