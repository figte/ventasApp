package com.inventarioFacturacion.app.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * DetalleVenta
 */
@Entity
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    private String tipo_detalle;

    @ManyToOne(fetch = FetchType.EAGER)
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE )
    private Venta venta;



    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

  

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    /**
     * @return the venta
     */
    public Venta getVenta() {
        return venta;
    }
    
    /**
     * @param tipo_detalle the tipo_detalle to set
     */
    public void setTipo_detalle(String tipo_detalle) {
        this.tipo_detalle = tipo_detalle;
    }
    
    /**
     * @return the tipo_detalle
     */
    public String getTipo_detalle() {
        return tipo_detalle;
    }
    
}