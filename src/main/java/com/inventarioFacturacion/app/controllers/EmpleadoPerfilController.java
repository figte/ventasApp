/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import com.inventarioFacturacion.app.components.ResponseJson;
import com.inventarioFacturacion.app.components.ValidateNullPointer;
import com.inventarioFacturacion.app.dao.IRolPerfil;
import com.inventarioFacturacion.app.model.dto.EmpleadoPerfilDto;
import com.inventarioFacturacion.app.model.dto.converter.EmpleadoConverter;
import com.inventarioFacturacion.app.model.entity.Empleado;
import com.inventarioFacturacion.app.model.entity.PerfilEmpleado;
import com.inventarioFacturacion.app.model.entity.Rol;
import com.inventarioFacturacion.app.model.entity.RolPerfil;
import com.inventarioFacturacion.app.services.EmpleadoPerfilService;
import com.inventarioFacturacion.app.services.PuestoService;
import com.inventarioFacturacion.app.services.RolService;
import com.inventarioFacturacion.app.services.SucursalService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class EmpleadoPerfilController.
 */
@Controller
@RequestMapping("administracion")
public class EmpleadoPerfilController {

	/** The empleado service. */
	// Dependencias
	private EmpleadoPerfilService empleadoService;

	/** The rol service. */
	private RolService rolService;

	/** The e converter. */
	private EmpleadoConverter eConverter;

	/** The res json. */
	private ResponseJson resJson;

	/** The null pontier. */
	private ValidateNullPointer nullPontier;

	/** The sucursal service. */
	private SucursalService sucursalService;
	/** The irol perfil. */

	private IRolPerfil irolPerfil;

	private PuestoService puestoservice;

	/**
	 * Instantiates a new empleado perfil controller.
	 *
	 * @param empleadoService the empleado service
	 * @param eConverter      the e converter
	 * @param resJson         the res json
	 * @param nullPontier     the null pontier
	 * @param rolService      the rol service
	 * @param sucursalService the sucursal service
	 */
	@Autowired // Injeccion
	private EmpleadoPerfilController(EmpleadoPerfilService empleadoService, EmpleadoConverter eConverter,
			ResponseJson resJson, ValidateNullPointer nullPontier, RolService rolService,
			SucursalService sucursalService, IRolPerfil irolPerfil, PuestoService puestoservice) {
		this.empleadoService = empleadoService;
		this.eConverter = eConverter;
		this.resJson = resJson;
		this.nullPontier = nullPontier;
		this.rolService = rolService;
		this.sucursalService = sucursalService;
		this.irolPerfil = irolPerfil;
		this.puestoservice = puestoservice;
	}

	/**
	 * Listar.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping(value = "/empleados")
	public String listar(Model model) {
		model.addAttribute("titulo", "AGREGAR EMPLEADO");
		model.addAttribute("tituloEditar", "MODIFICAR EMPLEADO");
		model.addAttribute("tituloTabla", "LISTADO DE EMPLEADOS");
		model.addAttribute("empleado", new Empleado());
		model.addAttribute("roles", rolService.listRolExeptoInactivo());
		model.addAttribute("puestos", puestoservice.listPuesto());
		model.addAttribute("sucursales", sucursalService.listSucursal());
		return "views/empleado_perfil/empleado_perfil_admin";
	}

	/**
	 * Gets the empleado by id.
	 *
	 * @param id the id
	 * @return the empleado by id
	 */
	@GetMapping(value = "/getEmpleadoById/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, Map<String, String>> getEmpleadoById(@PathVariable(name = "id") long id) {
		Map<String, Map<String, String>> response = null;
		// Validar datos
		id = nullPontier.validateToLong(id);
		Empleado empleado = empleadoService.empleadoById(id);
		if (empleado != null) {
			response = eConverter.listaEmpleados(empleado);
		}
		return response;

	}

	/**
	 * Gets the empleados.
	 *
	 * @param idSucursal the id sucursal
	 * @param cargo      the cargo
	 * @return the empleados
	 */
	@GetMapping(value = { "/getEmpleados", "/getEmpleados/{idSucursal}",
			"/getEmpleados/{idSucursal}/{cargo}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, List<Map<String, String>>> getEmpleados(
			@PathVariable(name = "idSucursal", required = false) Long idSucursal,
			@PathVariable(name = "cargo", required = false) String cargo) {

// Validar datos
		idSucursal = nullPontier.validateToLong(idSucursal);
		cargo = nullPontier.validateToString(cargo);
// Formateo a Json

		return Collections.singletonMap("data",
				eConverter.listaEmpleados(empleadoService.listEmpleados(cargo, idSucursal)));

	}

    /**
	 * Del rol.
	 *
	 * @param id the id
	 * @return the boolean
	 */
	@DeleteMapping(value = "/deleterempleado/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean delRol(@PathVariable(name = "id") Long id) {
		id = nullPontier.validateToLong(id);
		return empleadoService.DesactivarEmpleado(id);
	}

	/**
	 * Save.
	 *
	 * @param empleadoDto the empleado dto
	 * @param result      the result
	 * @return the response json
	 */
	@PostMapping(value = "/saveEmpleado", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseJson save(@ModelAttribute @Valid EmpleadoPerfilDto empleadoDto, BindingResult result) {
		resJson = resJson.validateJson(result);

		Empleado empleado = null;

		if (resJson.isValidated() == true) {
			empleado = empleadoService.SaveEmpleado(eConverter.convertirDtoAEmpleadoPerfilEmpleado(empleadoDto));
		}
		

		// asignar permisos

		if (empleado != null) {
			// System.out.println(empleadoService.BuscarPerfil(empleado.getContacto()));
			PerfilEmpleado prefil = new PerfilEmpleado();
			prefil.setId(empleado.getId());
			empleadoService.eliminarPermisos(prefil);
			for (String permiso : empleadoDto.getPermisos()) {
				Rol rol = new Rol(Long.parseLong(permiso));
				RolPerfil rp = new RolPerfil();
				rp.setPerfilEmpleado(prefil);
				rp.setRol(rol);
				irolPerfil.save(rp);
			}
		}
		System.out.println(resJson.toString());
		return resJson;

	}

	/* metodo para inicializar dataTabla vacia y evitar bug css recise false */
	@GetMapping(value = "initEmpleado", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, Map<String, String>> initDataTable() {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Map<String, String>> response = new HashMap<>();
		map.put("null", "");
		response.put("data", map);
		return response;
	}

	public PerfilEmpleado Perfil(String nombre) {
		return empleadoService.BuscarPerfil(nombre);
	}

}
