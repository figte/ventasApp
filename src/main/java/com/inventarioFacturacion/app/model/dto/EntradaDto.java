package com.inventarioFacturacion.app.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.inventarioFacturacion.app.dao.ICategoria;
import com.inventarioFacturacion.app.model.entity.Producto;

@Component
public class EntradaDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2785118042889567795L;
	/** The id. */
	private Long id;

	/** The detalle. */
	private String detalle;
	
	
	/** The detalle. */
	@NotEmpty(message = "* Campo requerido")
	private String compra;
	
	
	/** The detalle. */
	@NotEmpty(message = "* Campo requerido")
	private String documento;

	/** The tipo de entrada */
	@NotEmpty(message = "* Campo requerido")
	private String tipoDeEntrada;

	/** The cantidad. */
	@NotNull(message = "* Campo requerido")
	private Long cantidad;

	/** The cantidad. */
	@DecimalMin("0.01")
	@Digits(integer=100, fraction=2, message = "* Solo usar 2 decimales")
	private double precioCompra;
	
	/** The porcentaje. */
	@NotNull
	@Min(1)
	@Max(100)
	private int porcentaje;

	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		porcentaje = porcentaje.replace(" ","");
		if(porcentaje != "") {
		   this.porcentaje = Integer.parseInt( porcentaje);
		}
		
	}

	/** producto */
	@NotNull(message = "* Campo requerido")
	private String codigoProducto;

	public EntradaDto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDeEntrada() {
		return tipoDeEntrada;
	}

	public void setTipoDeEntrada(String tipoDeEntrada) {
		this.tipoDeEntrada = tipoDeEntrada;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		cantidad = cantidad.replace(" ",""); 
        if(cantidad == "") {
   
		}else {
	        	this.cantidad = Long.parseLong(cantidad);
		}
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(String precioCompra) {
		precioCompra = precioCompra.replace(" ", "");
		if (precioCompra == "") {

		} else {
			this.precioCompra = Double.parseDouble(precioCompra);
		}

	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		if(codigoProducto.equals("0") != true) {
			this.codigoProducto = codigoProducto;	
		}
		
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	public String getCompra() {
		return compra;
	}

	public void setCompra(String compra) {
		if(compra.equals("0") == true) compra = "";
		this.compra = compra;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "EntradaDto [id=" + id + ", detalle=" + detalle + ", compra=" + compra + ", documento=" + documento
				+ ", tipoDeEntrada=" + tipoDeEntrada + ", cantidad=" + cantidad + ", precioCompra=" + precioCompra
				+ ", porcentaje=" + porcentaje + ", codigoProducto=" + codigoProducto + "]";
	}
   
	
}
