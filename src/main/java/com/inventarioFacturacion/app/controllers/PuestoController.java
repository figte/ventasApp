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
import com.inventarioFacturacion.app.model.dto.converter.PuestosConverter;
import com.inventarioFacturacion.app.model.entity.Puesto;
import com.inventarioFacturacion.app.services.PuestoService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class RolController.
 */
@Controller
@RequestMapping("administracion")
public class PuestoController {

	/** The rol imp. */
	private PuestoService puestoImp;

	/** The res joson. */
	private ResponseJson resJoson;

	/** The puesto converter. */
	private PuestosConverter puestoConverter;

	/**
	 * Instantiates a new rol controller.
	 *
	 * @param rolImp       the rol imp
	 * @param resJoson     the res joson
	 * @param rolConverter the rol converter
	 */
	@Autowired
	private PuestoController(PuestoService puestoImp, ResponseJson resJoson, PuestosConverter puestoConverter) {
		this.puestoImp = puestoImp;
		this.resJoson = resJoson;
		this.puestoConverter = puestoConverter;
	}

	/**
	 * Listar.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping(value = "/puestos")
	public String listar(Model model) {
		model.addAttribute("titulo", "AGREGAR PUESTO");
		model.addAttribute("tituloEditar", "MODIFICAR CARGOS");
		model.addAttribute("tituloTabla", "LISTADO DE CARGOS");
		model.addAttribute("puesto", new Puesto());
		return "views/Puestos/puestos";
	}

	/**
	 * Gets the Puestos.
	 *
	 * @return the puestos
	 */
	@GetMapping(value = "/getPuestos", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, List<Map<String, String>>> getRoles() {
		return Collections.singletonMap("data", puestoConverter.listaPuestos(puestoImp.listPuesto()));
	}

	@PostMapping(value = "/savePuesto", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseJson save(@ModelAttribute @Valid Puesto puesto, BindingResult result) {
		resJoson = resJoson.validateJson(result);
		if (resJoson.isValidated() == true) {
			puestoImp.savePuesto(puesto);
		}
		System.out.println(resJoson.isValidated());
		return resJoson;
	}

	@DeleteMapping(value = "/deletePuesto/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean delRol(@PathVariable(name = "id") Long id) {
		boolean response = false;
		try {
			response = puestoImp.deletePuesto(id);
		} catch (Exception e) {
			throw new ProcessingEntityException("Error al eliminar el registro");
		}
		return response;
	}
 }