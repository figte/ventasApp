package com.inventarioFacturacion.app.model.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Serie
 */
@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String serie;

    private Integer correlativoActual;

    private Integer inicio;

    private Integer fin;

    private String estado;

    //CONSTRUCTORES

        public Serie() {
            
        }

        public Serie(String serie,Integer correlativoActual,Integer inicio, Integer fin) {
            this.serie=serie;
            this.correlativoActual=correlativoActual;
            this.inicio=inicio;
            this.fin=fin;
            
        }
    //SETTER Y GETTER

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
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
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param correlativoActual the correlativoActual to set
     */
    public void setCorrelativoActual(Integer correlativoActual) {
        this.correlativoActual = correlativoActual;
    }

    /**
     * @return the correlativoActual
     */
    public Integer getCorrelativoActual() {
        return correlativoActual;
    }
    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the inicio
     */
    public Integer getInicio() {
        return inicio;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(Integer fin) {
        this.fin = fin;
    }

    /**
     * @return the fin
     */
    public Integer getFin() {
        return fin;
    }



}