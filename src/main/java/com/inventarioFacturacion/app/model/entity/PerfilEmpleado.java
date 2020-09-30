/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class PerfilEmpleado.
 */
@Entity
@Table(name="perfiles_Empleados")
public class PerfilEmpleado implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8737290627825047739L;

	/** The id. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
	private long id;
    
	/** The nombre perfil. */
    @Column(name = "nombre", unique = true)
	private String nombrePerfil;
	
	/** The password perfil. */
    @Column(name = "password")
	private String passwordPerfil;
    
	/** The create at. */
	@Column(name = "create_at", updatable = false)
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyy-mm-dd")
	private Date createAt;
	
  
	/** The update at. */
	@Column(name = "update_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date updateAt;
    
    /** The rol perfil. */
    @OneToMany(mappedBy ="perfilEmpleado"  ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RolPerfil>  rolPerfil;
    
    /** The estado. */
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="estado_id")
    private Estado estado;
    
    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public Estado getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/** The empleados. */
	@OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
	@JoinColumn(name = "empleado_id")
    private Empleado empleados;
    
	public PerfilEmpleado() {
		rolPerfil = new ArrayList<RolPerfil>();
	}

	public PerfilEmpleado(String nombre, String password) {
		super();
		this.nombrePerfil = nombre;
		this.passwordPerfil = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombrePerfil;
	}

	public void setNombre(String nombre) {
		this.nombrePerfil = nombre;
	}


	public String getPassword() {
		return passwordPerfil;
	}


	public void setPassword(String password) {
		this.passwordPerfil = password;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public Date getUpdateAt() {
		return updateAt;
	}


	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}


	public List<RolPerfil> getRolPerfil() {
		return rolPerfil;
	}

	public void setRolPerfil(List<RolPerfil> rolPerfil) {
		this.rolPerfil = rolPerfil;
	}


	public Empleado getEmpleados() {
		return empleados;
	}


	public void setEmpleados(Empleado empleados) {
		this.empleados = empleados;
	}
	

	@PreUpdate
	private void preUpdate(){
    	this.estado = new Estado(1l);
		this.updateAt = new Date();
    }
    

    @PrePersist
    private void prepersis(){
    	this.estado = new Estado(1l);
    	this.createAt = new Date();
		this.updateAt = new Date();		
    }

	@Override
	public String toString() {
		return "PerfilEmpleado [id=" + id + ", nombrePerfil=" + nombrePerfil + ", passwordPerfil=" + passwordPerfil
				+ ", createAt=" + createAt + ", updateAt=" + updateAt + ", rolPerfil=" + rolPerfil + ", estado="
				+ estado + ", empleados=" + empleados + "]";
	}

	
 
}
