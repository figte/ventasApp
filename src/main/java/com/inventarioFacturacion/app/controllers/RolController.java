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
import com.inventarioFacturacion.app.model.dto.converter.RolConverter;
import com.inventarioFacturacion.app.model.entity.Rol;
import com.inventarioFacturacion.app.services.RolService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class RolController.
 */
@Controller
@RequestMapping("administracion")
public class RolController {
	
	
	/** The rol imp. */
	private RolService rolImp;
	
	/** The res joson. */
	private ResponseJson resJoson;
	
	/** The rol converter. */
	private RolConverter rolConverter;
 
	
	/**
	 * Instantiates a new rol controller.
	 *
	 * @param rolImp the rol imp
	 * @param resJoson the res joson
	 * @param rolConverter the rol converter
	 */
	@Autowired
	private RolController(RolService rolImp, ResponseJson resJoson, RolConverter rolConverter) {
		this.rolImp 		= rolImp;
		this.resJoson   	= resJoson;
		this.rolConverter 	= rolConverter;
	}
	
	/**
	 * Listar.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping(value = "/roles")
	public String listar(Model model) {
		model.addAttribute("titulo", "AGREGAR ROL");
		model.addAttribute("tituloEditar", "MODIFICAR ROL");
		model.addAttribute("tituloTabla", "LISTADO DE ROLES");
        model.addAttribute("rol", new Rol());
		return "views/Rol/roles_admin";	
	}
	
	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	@GetMapping(value="/getroles", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Map<String, List<Map<String, String>>> getRoles() {
		   return Collections.singletonMap("data", rolConverter.listaRol(rolImp.listRol()));		   
	}
	
	/**
	 * Del rol.
	 *
	 * @param id the id
	 * @return the boolean
	 */
	@DeleteMapping(value="/deleterol/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Boolean delRol(@PathVariable(name = "id") String id) {
		boolean response = false;     
		try {
			   response =  rolImp.deleteRol(Long.parseLong(id));
		} catch (Exception e) {
			 throw new ProcessingEntityException("El Rol cuenta con registros, para eliminarlo primero elimine todas sus dependencias");
		}	   
		return response;
	}
	
	
	/**
	 * Save.
	 *
	 * @param rol the rol
	 * @param result the result
	 * @return the response json
	 */
	@PostMapping(value = "/saveRol", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseJson save(@ModelAttribute @Valid Rol rol, BindingResult result) {		
		resJoson = resJoson.validateJson(result);
		if(resJoson.isValidated() == true) {rolImp.saveRol(rol);}
		System.out.println(resJoson.isValidated());
        return resJoson;		
	}
	
	
}
