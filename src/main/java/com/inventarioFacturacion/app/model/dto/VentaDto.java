package com.inventarioFacturacion.app.model.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * VentaDto
 */
@Component
public class VentaDto {

    private Long id;

    @NotBlank(message = "*Campo requerido")
    private String cliente;

    private String tipoDocumento;

    private String observacion;

    @NotNull(message = "*campo requerido")
    @Min(value = 1, message = "*campo requerido, no ha configurado una serie")
    private Long idSerie;

    @NotNull(message = "*campo requerido")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    private Double sumas;

    private Double iva;

    private Double cesc;

    private Double exento;

    private Double noSujeto;
    
    private Double total;


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
     * @param idSerie the idSerie to set
     */
    public void setIdSerie(Long idSerie) {
        this.idSerie = idSerie;
    }
    /**
     * @return the idSerie
     */
    public Long getIdSerie() {
        return idSerie;
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