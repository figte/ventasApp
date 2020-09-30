package com.inventarioFacturacion.app.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.inventarioFacturacion.app.components.ResponseJson;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.dto.ProductoDto;
import com.inventarioFacturacion.app.model.dto.converter.ProductoConverter;
import com.inventarioFacturacion.app.model.entity.Producto;
import com.inventarioFacturacion.app.services.ProductoService;

@Controller
@RequestMapping("inventario")
public class ProductoController {
	@Autowired
	private ProductoConverter productoConverter;
	
	@Autowired
	private ProductoService productoService;

	@Autowired
	private ResponseJson resJoson;

	//Listar entradas
	@GetMapping(value = "/wh/{categoriaId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String,List<Map<String,String>>> ListEntradas(@PathVariable(name = "categoriaId") Long categoriaId){
		return Collections.singletonMap("data", productoConverter.listaProductosByCategoria(productoService.lisProductoByCategoria(categoriaId)));
	}

	@GetMapping(value = "/productos")
	public String listar( Model model) {
		model.addAttribute("titulo", "AGREGAR PRODUCTO");
		model.addAttribute("tituloEditar", "MODIFICAR PRODUCTO");
		model.addAttribute("tituloTabla", "LISTADO DE PRODUCTOS");
		model.addAttribute("producto", new ProductoDto());
		model.addAttribute("proveedores", productoService.listProveedor());
		model.addAttribute("categorias", productoService.listCategoria());
		
		return "views/Productos/productos";
	}

	@GetMapping(value = "/getproductos", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, List<Map<String, String>>> getSeries() {
		return Collections.singletonMap("data", productoConverter.listaProductos(productoService.listProducto()));
	}

	@PostMapping(value = "/saveProducto", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseJson save(@ModelAttribute @Valid ProductoDto producto, BindingResult result) {
		resJoson = resJoson.validateJson(result);
		try {
			if(resJoson.isValidated() == true) {
				Producto entity=productoConverter.getProducto(producto);
				productoService.saveOrUpdate(entity);
			}
			return resJoson;
		} catch (Exception e) {
			//TODO: handle exception
			System.err.println("Error: "+e.getMessage());
			return null;
		}
		
	}
	
	@DeleteMapping(value = "/deleteProducto/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean delelete(@PathVariable(name = "id") String id) {
		boolean response = true;  
		try {
			productoService.delete(productoService.getProducto(id));
		} catch (Exception e) {
			throw new ProcessingEntityException("error al eliminar "+e.getMessage());
		}
		return response;
	}
	@GetMapping(value = "/getproductos_categoria/{categoriaID}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, List<Map<String, String>>> getProductos(@PathVariable(name = "categoriaID") Long categoriaID) {
		return Collections.singletonMap("data", productoConverter.listaProductos(productoService.lisProductoByCategoria(categoriaID)));
	}
	
	

	
}
