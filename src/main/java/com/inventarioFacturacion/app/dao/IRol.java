/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.inventarioFacturacion.app.model.entity.Rol;


// TODO: Auto-generated Javadoc
/**
 * The Interface IRol.
 */
public interface IRol  extends   PagingAndSortingRepository<Rol, Long>{
       
       /**
        * Find all exepted inactivo.
        *
        * @return the list
        */
       @Query("select roles from Rol roles where roles.nombre != 'INACTIVO'")
       List<Rol> findAllExeptedInactivo();
       
       /**
        * Find by nombre.
        *
        * @param nombre the nombre
        * @return the rol
        */
       Rol findByNombre(String nombre);
       
}
