package com.inventarioFacturacion.app.dao;

import com.inventarioFacturacion.app.model.entity.Proveedor;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * IProveedor
 */
public interface IProveedor extends PagingAndSortingRepository<Proveedor,Long> {

    
}