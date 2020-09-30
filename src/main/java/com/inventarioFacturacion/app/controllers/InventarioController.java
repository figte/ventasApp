/*
 * Fecha: 12-19-2019
 * @Jaime_Ramirez
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class InventarioController.
 */
@Controller
@RequestMapping("inventario")
public class InventarioController {
	
	/** The entrada service. */
	@Autowired
	private EntradaService entradaService;
	
	/** The categoria service. */
	@Autowired
	private CategoriaService categoriaService;
	
	/** The entrada converter. */
	@Autowired
	private EntradaConverter entradaConverter;
	
	/** The producto service. */
	@Autowired
	private ProductoService productoService;
    
	/** The res json. */
	@Autowired
	private ResponseJson resJson;
	
	/**
	 * Listado y vista principal de entradas.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping(value = "/entradas")
	public String  entradas(Model model) {
		model.addAttribute("categorias", categoriaService.Listar());
		return "views/Entradas/entradas";
	}
	
	/**
	 * salidas.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping(value = "/salidas")
	public String  salidas(Model model) {
		//model.addAttribute("categorias", categoriaService.Listar());
		return "views/Salidas/salidas";
	}
	
	
	/**
	 * Guardar.
	 *
	 * @return the hash map
	 */
	@GetMapping(value = "/saveEntrada")
	@ResponseBody
	public HashMap<String, String> guardar() {
		HashMap<String, String> response = new HashMap<>();
		Entradas entrada = new Entradas();
		Producto producto = new Producto();
		producto.setCodigo("T01");
		entrada.setCantidad(12l);
		entrada.setDatalle("fsgdg");
		//entrada.setProducto(producto);
		entrada.setTipoDeEntrada("test");
		entradaService.saveOrUpdateentradas(entrada);
		response.put("messaje", "hola");
		response.put("status", "202");
		return response;
	}
	
	/**
	 * Inits the data table.
	 *
	 * @return the map
	 */
	/* metodo para inicializar dataTabla vacia y evitar bug css recise false */
	@GetMapping(value = "/initEntradas", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, Map<String, String>> initDataTable(){
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Map<String, String>> response = new HashMap<>();
		map.put("null", "");
		response.put("data", map);
		return response;
	}
	
	/**
	 * List entradas.
	 *
	 * @param inicio the inicio
	 * @param fin the fin
	 * @return the map
	 */
	//Listar entradas
	@GetMapping(value = "/list_entradas/{fecha1}/{fecha2}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String,List<Map<String,String>>> ListEntradas(@PathVariable(name = "fecha1") String inicio,@PathVariable(name = "fecha2") String fin){
		return Collections.singletonMap("data", entradaConverter.listaEntradas(entradaService.Listar(inicio,fin)));
	}
	
	/**
	 * Save.
	 *
	 * @param entrada the entrada
	 * @param result the result
	 * @return the response json
	 */
	@PostMapping(value = "/saveEntrada", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseJson save(@ModelAttribute @Valid EntradaDto entrada, BindingResult result) {
		resJson = resJson.validateJson(result);
		Entradas entradas = new Entradas();
		Producto producto = new Producto();
		if (resJson.isValidated() == true) {
			System.out.println(entrada.toString());
			entradas.setCantidad(entrada.getCantidad());
			entradas.setDatalle(entrada.getDetalle());
			entradas.setPorcentaje(entrada.getPorcentaje());
			entradas.setPrecioCompra(entrada.getPrecioCompra());
			entradas.setTipoDeEntrada(entrada.getTipoDeEntrada());
			producto.setCodigo(entrada.getCodigoProducto());
			entradas.setProducto(producto);
			entradas.setDocumento(entrada.getCompra());
			entradas.setCodigo(entrada.getDocumento());
			entradaService.saveOrUpdateentradas(entradas);
		}
		
		return resJson;
	}

}
