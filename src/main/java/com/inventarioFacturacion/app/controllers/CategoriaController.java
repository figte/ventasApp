/*
 * Fecha: 11-01-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inventarioFacturacion.app.dao.ICategoria;
import com.inventarioFacturacion.app.model.dto.converter.CategoriaConverter;
import com.inventarioFacturacion.app.model.entity.Categoria;
import com.inventarioFacturacion.app.services.CategoriaService;

/**
 * The Class CategoriaController.
 */
@Controller
@RequestMapping("administracion")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaServive;
	
	@Autowired
	private CategoriaConverter categoriaConverter;

	@GetMapping(value = "/categorias")
	public String listar(Model model) {
		return "views/Categorias/categorias";
	}

	@GetMapping(value = "/saveCategoria")
	@ResponseBody
	public HashMap<String, String> guardar() {
		HashMap<String, String> response = new HashMap<>();
		Categoria categoria = new Categoria();
		categoria.setNombre("test");
		//categoria.setDetalle("detalle");
		categoria.setEstado(1);
		categoriaServive.saveOrUpdateCategoria(categoria);
		response.put("messaje", "hola");
		response.put("status", "202");
		return response;
	}
	@GetMapping(value = "/EditCategoria")
	@ResponseBody
	public HashMap<String, String> Modificar() {
		HashMap<String, String> response = new HashMap<>();
		Categoria categoria = new Categoria();
		categoria.setId(3l);
		categoria.setNombre("Mod");
		categoria.setDetalle("detalle");
		categoria.setEstado(1);
		categoriaServive.saveOrUpdateCategoria(categoria);
		response.put("messaje", "hola");
		response.put("status", "202");
		return response;
	}
	
	@GetMapping(value = "/eliminarCategoria")
	@ResponseBody
	public HashMap<String, String> eliminar(){
		HashMap<String, String> response = new HashMap<>();
		Categoria categoria = new Categoria();
		categoria.setId(3l);
		categoria.setNombre("Mod");
		categoria.setDetalle("detalle");
		categoria.setEstado(1);
		try {
			categoriaServive.disabeCategoria(1l);
		} catch (Exception e) {
			response.put("error", e.getMessage());
		}
		response.put("messaje", "hola");
		response.put("status", "202");
		return response;
	}
	
	@GetMapping(value = "/listarCategorias")
	@ResponseBody
	public List<Map<String, String>> Listar(){
		return categoriaConverter.listaCategorias(categoriaServive.Listar());
	}

}
