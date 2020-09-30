/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;


import com.inventarioFacturacion.app.model.entity.Sucursal;

/**
 * The Interface ISucursal.
 */
public interface ISucursal extends PagingAndSortingRepository<Sucursal, Long>{
       public Sucursal findByNombre(String nombre);
}
