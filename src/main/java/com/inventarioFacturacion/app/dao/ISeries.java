package com.inventarioFacturacion.app.dao;

import com.inventarioFacturacion.app.model.entity.Serie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * ISeries
 */
@Repository
public interface ISeries extends CrudRepository<Serie,Long>{
    // public Serie findBySerie(String serie);
    
}