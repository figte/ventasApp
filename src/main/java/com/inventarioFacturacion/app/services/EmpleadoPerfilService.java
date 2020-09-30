/*
 * Fecha: 09-22-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.services;

//https://www.mkyong.com/spring-boot/spring-rest-error-handling-example/
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inventarioFacturacion.app.dao.IEmpleado;
import com.inventarioFacturacion.app.dao.IPerfilEmpleado;
import com.inventarioFacturacion.app.dao.IRolPerfil;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.entity.Empleado;
import com.inventarioFacturacion.app.model.entity.PerfilEmpleado;

// TODO: Auto-generated Javadoc
/**
 * Servicio encargado de gestionar la operaciones CRUD proporcionadas por la
 * interface y realizar validaciones.
 *
 * @Jaime_Ramírez 19-9-2019
 */

@Service
public class EmpleadoPerfilService {

	/** The iempleado. */
	// Atributos
	@Autowired
	private IEmpleado iempleado;

	/** The irol perfil. */
	@Autowired
	private IRolPerfil irolPerfil;

	/** The iperfil. */
	@Autowired
	private IPerfilEmpleado iperfil;

	/** The estado. */
	private static String ESTADO = "ACTIVO";

	/**
	 * Instantiates a new empleado perfil service.
	 */
	public EmpleadoPerfilService() {

	}

	/**
	 * List empleados.
	 *
	 * @param cargo      the cargo
	 * @param idSucursal the id sucursal
	 * @return the list
	 */
	@Transactional
	public List<Empleado> listEmpleados(String cargo, Long idSucursal) {
		return empleados(cargo, idSucursal);
	}

	/**
	 * Empleado by id.
	 *
	 * @param id the id
	 * @return the empleado
	 */
	@Transactional
	public Empleado empleadoById(long id) {
		Empleado empleado = null;
		try {
			empleado = iempleado.findById(id).orElse(null);
			
		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		return empleado;
	}

	/**
	 * Desactivar empleado.
	 *
	 * @param idEmpleado the id empleado
	 * @return true, if successful
	 */
	@Transactional
	public boolean DesactivarEmpleado(Long idEmpleado) {
		Boolean response = true;
		Long DESACTIVAR = 2l;
		try {
			iperfil.DesactivarEmpleado(DESACTIVAR, idEmpleado);
		} catch (Exception e) {
			System.out.print("Error: " + e);
			response = false;
		}
		return response;
	}
	
	

	/**
	 * Save empleado.
	 *
	 * @param empleado the empleado
	 * @return true, if successful
	 */
	@Transactional
	public Empleado SaveEmpleado(Empleado empleado) {
		Empleado response = null;
		try {
			/*
			 * En este btipo de casos OnetoOne se debe pasar el objeto A al Objeto B y
			 * viceversa para poder guardar una relacion de este tipo
			 */
			if (empleado.getId() == 0L) {
				response = iempleado.save(empleado);
			} else {
				response = iempleado.save(empleado);
			}

		} catch (Exception e) {
			throw new ProcessingEntityException("Error al guardar el Empleado");
		}
		return response;
	}

	/**
	 * * Validacion de filtro de empleados por sucursales ****.
	 *
	 * @param cargo      the cargo
	 * @param idSucursal the id sucursal
	 * @return the list
	 */
	List<Empleado> empleados(String cargo, Long idSucursal) {
		List<Empleado> list = null;
		switch (idSucursal.intValue()) {
		case 0:
			if (cargo.equals("all") == true) {// Si es igual a all y la sucursal es iguala 0(todas las sucursales)

				list = (List<Empleado>) iempleado.findAll(ESTADO); // Listar todos los registros
			} else {
				list = iempleado.perfilByCargoAndEstado(cargo, ESTADO);
			}
			break;
		default:
			// Sino se desea filtrar una sucursal en especifico
			if (cargo.equals("all") == true) {// Verificar si necesita buscar todos los cargos
				list = iempleado.findBySucursal(idSucursal, ESTADO);// traer todos los empleados
			} else {
				try {
					list = iempleado.findByCargoAndSucursal(idSucursal, ESTADO, cargo);// Filtrar por cargo y sucursal
				} catch (Exception e) {
					System.out.print("Error: " + e);
				}
			}
			break;
		}
		return list;
	}
	
	//Eliminamos los permisos relacionados con el usuario
	@Transactional
	public boolean eliminarPermisos(PerfilEmpleado perfil) {
		irolPerfil.deleteByPerfilEmpleado(perfil);
		return true;
	}
	
	@Transactional
	public PerfilEmpleado BuscarPerfil(String nombre) {
		PerfilEmpleado perfil = null;
		try {
			perfil =  iperfil.findAllByNombrePerfil(nombre);
		} catch (Exception e) {
			System.out.println("Error: "+e);
		}
		return perfil;
	}

}
