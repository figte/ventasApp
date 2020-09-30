package com.inventarioFacturacion.app.dao;

import com.inventarioFacturacion.app.model.entity.Venta;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * IVenta
 */
@Repository
public interface IVenta extends CrudRepository<Venta,Long>{

    
}