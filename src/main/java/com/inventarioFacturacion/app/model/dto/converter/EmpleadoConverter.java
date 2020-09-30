/*
 * Fecha: 09-22-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.model.dto.converter;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.inventarioFacturacion.app.components.ValidateNullPointer;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.dto.EmpleadoPerfilDto;
import com.inventarioFacturacion.app.model.entity.Empleado;
import com.inventarioFacturacion.app.model.entity.PerfilEmpleado;
import com.inventarioFacturacion.app.model.entity.RolPerfil;
import com.inventarioFacturacion.app.model.entity.Sucursal;
import com.inventarioFacturacion.app.services.EmpleadoPerfilService;
// TODO: Auto-generated Javadoc

/**
 * Componente encargado de convertir manualmente a formato Json.
 *
 * @Jaime_Ramírez 19-9-2019
 */
@Component
public class EmpleadoConverter {

	/** The null pontier. */
	@Autowired
	private ValidateNullPointer nullPontier;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmpleadoPerfilService test;

	/**
	 * Lista empleados.
	 *
	 * @param lista the lista
	 * @return the list
	 */
	public List<Map<String, String>> listaEmpleados(List<Empleado> lista) {

		List<Map<String, String>> listOfMaps = new ArrayList<Map<String, String>>();

		Map<String, String> map = null;
		List<String> roles = null;
		try {
			for (Empleado element : lista) {
				roles = new ArrayList<>();
				map = new HashMap<String, String>();
				map.put("correlativo", Long.toString(element.getId()));
				map.put("nombre", element.getNombre());
				map.put("apellido", element.getApellido());
				map.put("detalle", element.getDetalle());
				map.put("direccion", element.getDireccion());
				map.put("contacto", element.getContacto());
				map.put("profecion", element.getProfesion());
				map.put("sucursal", element.getSucursal().getNombre());
				map.put("cargo", element.getCargo());
				try {
					for (RolPerfil r : element.getPerfil().getRolPerfil()) {
						roles.add(r.getRol().getNombre());
					}
					map.put("roles", roles.toString());
				} catch (Exception e) {
					map.put("roles", "Sin_permisos");
				}
				map.put("estadoPerfil", element.getPerfil().getEstado().getNombre());
				map.put("editar",
						"<button type='button' class='btn btn-outline-success openEdit'><i class='fa fa-edit'></i> </button>");
				map.put("eliminar",
						"<button type='button' class='btn btn-outline-danger openDelete'><i class='fa fa-minus'></i> </button>");
				listOfMaps.add(map);
			}

		} catch (Exception e) {
			throw new ProcessingEntityException("Error al procesar el Empleado");
		}

		// System.out.print(listOfMaps);
		return listOfMaps;
	}

	/**
	 * Lista empleados.
	 *
	 * @param empleado the empleado
	 * @return the map
	 */
	// Convert List to format Json
	public Map<String, Map<String, String>> listaEmpleados(Empleado empleado) {

		Map<String, String> datos = new HashMap<>();
		Map<String, Map<String, String>> response = new HashMap<>();
		try {
			datos.put("nombre", empleado.getNombre());
			datos.put("apellido", empleado.getApellido());
			datos.put("direccion", empleado.getDireccion());
			datos.put("puesto", empleado.getCargo());
			datos.put("sucursal", empleado.getSucursal().getNombre());
			datos.put("sucursal_id", empleado.getSucursal().getId().toString());
			datos.put("fecha_nac", empleado.getEdad().toString());
			datos.put("profesion", empleado.getProfesion());
			datos.put("contacto", empleado.getContacto());
			List<Long> roles = new ArrayList<>();
			for (RolPerfil rol : empleado.getPerfil().getRolPerfil()) {
				roles.add(rol.getRol().getId());
			}
			datos.put("roles", roles.toString());
			response.put("data", datos);
		} catch (Exception e) {
			throw new ProcessingEntityException("Error al listar el empleado");
		}
		return response;
	}

	/**
	 * Convertir dto A empleado perfil empleado.
	 *
	 * @param dto the dto
	 * @return the empleado
	 */
	public Empleado convertirDtoAEmpleadoPerfilEmpleado(EmpleadoPerfilDto dto) {

		long id = nullPontier.validateToLong(dto.getId());
		PerfilEmpleado perfil = null;
		PerfilEmpleado validarPerfil = null;
		Empleado empleado = null;

		validarPerfil = test.BuscarPerfil(dto.getContacto());
        /**
         * validación para resolver duplicidad en el nombre del perfil de usuario
         * Es sencible a mayusculas y minusculas
         * */
		if (validarPerfil == null) {
			//System.out.println("validar null");
			//pass
		} else {
			//Si el dato es igual al existente
			if (dto.getId() == validarPerfil.getId() && dto.getContacto().equals(validarPerfil.getNombre())) {
                //pass
			} else {
				//Enviar exeption
				throw new ProcessingEntityException("DUPLICADO");
			}
		}

		try {
			String username = dto.getContacto().replace(" ", "");
			// System.out.println(validarPerfil.getNombre()+"este es");
			if (id != 0l) {
				Sucursal sucursal = new Sucursal(dto.getEmpresa());
				perfil = new PerfilEmpleado(username, passwordEncoder.encode("password"));
				perfil.setId(id);
				empleado = new Empleado(dto.getNombre(), dto.getApellido(), dto.getDireccion(), dto.getProfesion(),
						dto.getContacto(), dto.getCargo(), sucursal, perfil, dto.getEdad());
				empleado.setId(id);
				perfil.setEmpleados(empleado);
			} else {
				Sucursal sucursal = new Sucursal(dto.getEmpresa());
				perfil = new PerfilEmpleado(username, passwordEncoder.encode("password"));
				empleado = new Empleado(dto.getNombre(), dto.getApellido(), dto.getDireccion(), dto.getProfesion(),
						dto.getContacto(), dto.getCargo(), sucursal, perfil, dto.getEdad());

				perfil.setEmpleados(empleado);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e);
			throw new ProcessingEntityException("Error al procesar el Empleado");
		}

		return empleado;
	}
}
