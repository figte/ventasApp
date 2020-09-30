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
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.dto.converter.SucursalConverter;
import com.inventarioFacturacion.app.model.entity.Sucursal;
import com.inventarioFacturacion.app.services.SucursalService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class SucursalController.
 */
@Controller
@RequestMapping("administracion")
public class SucursalController {
	
	/** The sucursal service. */
	private SucursalService sucursalService;
	
	/** The res joson. */
	private ResponseJson resJoson;
	
	/** The sucursal converter. */
	private SucursalConverter sucursalConverter;
	
	/**
	 * Instantiates a new sucursal controller.
	 *
	 * @param sucursalService the sucursal service
	 * @param resJoson the res joson
	 * @param sucursalConverter the sucursal converter
	 */
	@Autowired
	private SucursalController(SucursalService sucursalService, ResponseJson resJoson,SucursalConverter sucursalConverter) {
		    this.sucursalService = sucursalService;
		    this.resJoson        = resJoson;
		    this.sucursalConverter    = sucursalConverter;
	} 
	
	/**
	 * Listar.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping(value = "/sucursales")
	public String listar(Model model) {
		model.addAttribute("titulo", "AGREGAR SUCURSAL");
		model.addAttribute("tituloEditar", "MODIFICAR SUCURSAL");
		model.addAttribute("tituloTabla", "LISTADO DE SUCURSALES");
		model.addAttribute("sucursal", new Sucursal());
		return "views/sucursal/sucursal_admin";
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	@GetMapping(value = "/getsucursales", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, List<Map<String, String>>> getRoles() {
		return Collections.singletonMap("data", sucursalConverter.listaSucursal((List<Sucursal>) sucursalService.listSucursal()));
	}

	/**
	 * Del rol.
	 *
	 * @param id the id
	 * @return the boolean
	 */
	@DeleteMapping(value = "/deleteSucursal/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean delRol(@PathVariable(name = "id") Long id) {
		boolean response = true;  
		try {
			sucursalService.deleteSucursal(id);
		} catch (Exception e) {
			throw new ProcessingEntityException("La sucursal cuenta con registros, para eliminarla primero elimine todos sus dependencias");
		}
		return response;
	}

	/**
	 * Save.
	 *
	 * @param sucursal the sucursal
	 * @param result the result
	 * @return the response json
	 */
	@PostMapping(value = "/saveSucursal", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseJson save(@ModelAttribute @Valid Sucursal sucursal, BindingResult result) {
		resJoson = resJoson.validateJson(result);
		if(resJoson.isValidated() == true) {sucursalService.saveSucursal(sucursal);}
		return resJoson;
	}

}
