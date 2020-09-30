package com.inventarioFacturacion.app.model.entity;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressAjWarnings
@Entity
@Table(name = "salidas")
public class Salidas implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2785118042889567795L;
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	/** The nombre. */
	@Column(name = "detalle")
	private String datalle;
	
	/** The tipo de Salida */
	@Column(name = "tipo_salida", nullable = false)
	private String tipoSalida;
	
	/** The cantidad. */
	@Column(name = "cantidad", nullable = false)
	private Long cantidad;
	
	@Column(name = "create_at",  updatable = false, nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date createAt;
	
	/** producto */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Producto producto;
	
	public Salidas() {
    	
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatalle() {
		return datalle;
	}

	public void setDatalle(String datalle) {
		this.datalle = datalle;
	}

	public String getTipoSalida() {
		return tipoSalida;
	}

	public void setTipoSalida(String tipoSalida) {
		this.tipoSalida = tipoSalida;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
