package com.inventarioFacturacion.app.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.deser.SettableAnyProperty;

/**
 * Venta
 */
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    private Serie correlativo;

    private String cliente;

    private String tipoDocumento;

    private String observacion;

    private Double sumas;

    private Double iva;

    private Double cesc;

    private Double exento;

    private Double noSujeto;
    
    private Double total;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy ="venta")
    private List<DetalleVenta> detalles_venta;


    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }
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
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param correlativo the correlativo to set
     */
    public void setCorrelativo(Serie correlativo) {
        this.correlativo = correlativo;
    }
    /**
     * @return the correlativo
     */
    public Serie getCorrelativo() {
        return correlativo;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    /**
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param detallesVentas the detallesVentas to set
    */
    public void setDetallesVentas(List<DetalleVenta> detalles_venta) {
        this.detalles_venta = detalles_venta;
    }
    /**
     * @return the detallesVentas
    */
    public List<DetalleVenta> getDetallesVentas() {
        return detalles_venta;
    }
   
    /**
     * @param sumas the sumas to set
     */
    public void setSumas(Double sumas) {
        this.sumas = sumas;
    }
    /**
     * @return the sumas
     */
    public Double getSumas() {
        return sumas;
    }
    /**
     * @param iva the iva to set
     */
    public void setIva(Double iva) {
        this.iva = iva;
    }
    /**
     * @return the iva
     */
    public Double getIva() {
        return iva;
    }
    /**
     * @param cesc the cesc to set
     */
    public void setCesc(Double cesc) {
        this.cesc = cesc;
    }
    /**
     * @return the cesc
     */
    public Double getCesc() {
        return cesc;
    }
    /**
     * @param exento the exento to set
     */
    public void setExento(Double exento) {
        this.exento = exento;
    }
    /**
     * @return the exento
     */
    public Double getExento() {
        return exento;
    }
    /**
     * @param noSujeto the noSujeto to set
     */
    public void setNoSujeto(Double noSujeto) {
        this.noSujeto = noSujeto;
    }
    /**
     * @return the noSujeto
     */
    public Double getNoSujeto() {
        return noSujeto;
    }
    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }
    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

}