
package com.inventarioFacturacion.app.model.dto;


import java.math.BigInteger;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * SerieDto
 */
@Component
public class SerieDto {


    private Long id;

    @NotBlank(message = "*Campo requerido")
    private String serie;

    @NotNull(message = "*campo requerido")
    @Min(value = 1,message = "*tiene que colocar como minimo el valor 1")
  
    private Integer correlativoActual;

    @NotNull(message = "*campo requerido")
    @Min(value = 1,message = "*tiene que colocar como minimo el valor 1")
    private Integer inicio;

    @NotNull(message = "*campo requerido")
    @Min(value = 1,message = "*tiene que colocar como minimo el valor 1")
    private Integer fin;

    @NotBlank(message = "*Campo requerido")
    private String estado;

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

 
    //SETTER Y GETTER

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