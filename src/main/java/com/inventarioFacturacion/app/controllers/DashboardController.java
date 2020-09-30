package com.inventarioFacturacion.app.controllers;

import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inventarioFacturacion.app.components.ResponseJson;
import com.inventarioFacturacion.app.model.dto.EmpleadoPerfilDto;
import com.inventarioFacturacion.app.model.dto.EntradaDto;
import com.inventarioFacturacion.app.model.dto.converter.CategoriaConverter;
import com.inventarioFacturacion.app.model.dto.converter.EntradaConverter;
import com.inventarioFacturacion.app.model.entity.Empleado;
import com.inventarioFacturacion.app.model.entity.Entradas;
import com.inventarioFacturacion.app.model.entity.PerfilEmpleado;
import com.inventarioFacturacion.app.model.entity.Producto;
import com.inventarioFacturacion.app.model.entity.Rol;
import com.inventarioFacturacion.app.model.entity.RolPerfil;
import com.inventarioFacturacion.app.services.CategoriaService;
import com.inventarioFacturacion.app.services.EntradaService;
import com.inventarioFacturacion.app.services.ProductoService;

@Controller
@RequestMapping("dashboard")
public class DashboardController {
	/*
	@Autowired
	private EntradaService entradaService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private EntradaConverter entradaConverter;
	
	@Autowired
	private ProductoService productoService;
    
	@Autowired
	private ResponseJson resJson;*/
	/**
	 * Listado y vista principal de entradas
	 * 
	 * */
	@GetMapping(value = "/index")
	public String  entradas(Model model) {
		return "views/DashBoard/dashboard";
	}
	
	
	

}
