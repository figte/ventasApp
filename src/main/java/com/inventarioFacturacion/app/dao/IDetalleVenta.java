package com.inventarioFacturacion.app.dao;

import com.inventarioFacturacion.app.model.entity.DetalleVenta;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * IDetalleVenta
 */
@Repository
public interface IDetalleVenta extends CrudRepository<DetalleVenta,Long> {

}