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
@Table(name = "entradas")
public class Entradas implements Serializable{
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
	
	/** The nombre. */
	@Column(name = "documento_Compra")
	private String documento;
	
	/** The nombre. */
	@Column(name = "codigo_documento_compra")
	private String codigo;
	
	/** The tipo de entrada */
	@Column(name = "tipo_entrada", nullable = false)
	private String tipoDeEntrada;
	
	/** The cantidad. */
	@Column(name = "cantidad", nullable = false)
	private Long cantidad;
	
	/** The cantidad. */
	@Column(name = "precio_compra", nullable = false)
	private double precioCompra;
	
	/** The porcentaje. */
	@Column(name = "porcentaje", nullable = false)
	private int porcentaje;
	
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	/** producto */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Producto producto;
	
	/** The create at. */
	// @NotNull
	@Column(name = "create_at",  updatable = false, nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date createAt;
	public Entradas() {
    	
    	
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

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public String getTipoDeEntrada() {
		return tipoDeEntrada;
	}
	public void setTipoDeEntrada(String tipoDeEntrada) {
		this.tipoDeEntrada = tipoDeEntrada;
	}
	 public Producto getProducto() {
	 	return producto;
	 }
	 public void setProducto(Producto producto) {
	 	this.producto = producto;
	 }
	
	/**
	 * Sets the Pre persist
	 */
	@PrePersist
	public void setFechas() {
		this.createAt = new Date();
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return "Entradas [id=" + id + ", datalle=" + datalle + ", tipoDeEntrada=" + tipoDeEntrada + ", cantidad="
				+ cantidad + ", precioCompra=" + precioCompra + ", porcentaje=" + porcentaje + ", producto=" + producto
				+ ", createAt=" + createAt + "]";
	}
}
