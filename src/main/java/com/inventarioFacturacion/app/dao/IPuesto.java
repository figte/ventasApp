package com.inventarioFacturacion.app.dao;

import org.springframework.data.repository.CrudRepository;
import com.inventarioFacturacion.app.model.entity.Puesto;

public interface IPuesto extends CrudRepository<Puesto, Long>{
	 /**
     * Find by nombre.
     *
     * @param nombre the nombre
     * @return the Puesto
     */
    Puesto findByNombre(String nombre);
}
