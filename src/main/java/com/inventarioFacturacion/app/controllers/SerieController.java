package com.inventarioFacturacion.app.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.inventarioFacturacion.app.components.ResponseJson;
import com.inventarioFacturacion.app.dao.ISeries;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.dto.SerieDto;
import com.inventarioFacturacion.app.model.dto.converter.SerieConverter;
import com.inventarioFacturacion.app.model.entity.Serie;
import com.inventarioFacturacion.app.services.SerieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
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

/**
 * SerieController
 */
@Controller
@RequestMapping("administracion")
public class SerieController {
 
	
	/** The serie imp. */
	private SerieService serieServive;
	
	/** The serie converter. */
	private SerieConverter serieConverter;
	
	/** The res joson. */
	private ResponseJson resJoson;


	 
	private ISeries is;

	@Autowired
	private SerieController(SerieService serieServive, ResponseJson resJoson,SerieConverter serieConverter) {
		    this.serieServive = serieServive;
		    this.resJoson        = resJoson;
		    this.serieConverter    = serieConverter;
	} 

	/**
	 * Listar.
	 *
	 * @param model the model
	 * @return the string
	 */
    @GetMapping(value = "/series")
	public String listar( Model model) {
		model.addAttribute("titulo", "AGREGAR SERIE");
		model.addAttribute("tituloEditar", "MODIFICAR SERIE");
		model.addAttribute("tituloTabla", "LISTADO DE SERIES");
		model.addAttribute("serie", new SerieDto());
		
		return "views/Series/series";
	}

	/**
	 * Gets the series.
	 *
	 * @return the series
	 */
	@GetMapping(value = "/getseries", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, List<Map<String, String>>> getSeries() {
		return Collections.singletonMap("data", serieConverter.listaSeries((List<Serie>) serieServive.listSeries()));
	}

	@GetMapping(value = "/allSeries", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Serie> allSeries() {
		return serieServive.listSeries();
	}

	/**
	 * Del rol.
	 *
	 * @param id the id
	 * @return the boolean
	 */
	@DeleteMapping(value = "/deleteSerie/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean delSerie(@PathVariable(name = "id") Long id) {
		boolean response = true;  
		try {
			serieServive.deleteSerie(id);
		} catch (Exception e) {
			throw new ProcessingEntityException("La serie cuenta con registros, para eliminarla primero elimine todos sus dependencias");
		}
		return response;
	}

	/**
	 * Save.
	 *
	 * @param s the sucursal
	 * @param result the result
	 * @return the response json
	 */
	@PostMapping(value = "/saveSerie3", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public HashMap<String, String> save3( SerieDto s) {
		HashMap<String, String> response = new HashMap<>();

		// Serie se=new Serie();
		// se.setSerie(r.getParameter("serie"));
		// se.setCorrelativoActual(Integer.parseInt(r.getParameter("correlativoActual")));
		// se.setInicio(Integer.parseInt(r.getParameter("inicio")));
		// se.setFin(Integer.parseInt(r.getParameter("fin")));
	
		// if(resJoson.isValidated() == true) {serieServive.saveSerie(s);}
		// serieServive.saveSerie(se);
		
		
		response.put("status", "202");
		response.put("validated", "true");
		return response;
	}

	@PostMapping(value = "/saveSerie", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseJson save(@ModelAttribute @Valid SerieDto serie, BindingResult result) {
		resJoson = resJoson.validateJson(result);
		//validacion
		
		if(serie.getCorrelativoActual()!=null && serie.getFin()!=null && serie.getInicio()!=null){
         if(serie.getCorrelativoActual()<serie.getInicio()){
			resJoson.setValidated(false);
			resJoson.getErrorMessages().put("correlativoActual", "el correlativo actual no puede se menor al inicio");
		 }
		 if(serie.getCorrelativoActual()<serie.getInicio()){
			resJoson.setValidated(false);
			resJoson.getErrorMessages().put("correlativoActual", "el correlativo actual no puede se menor al inicio");
		 }
		 
		 if(serie.getInicio()>serie.getFin()){
			resJoson.setValidated(false);
			resJoson.getErrorMessages().put("inicio", "el inicio  no puede se menor al fin");
		 }
		}


		if(resJoson.isValidated() == true) {
			Serie s=serieConverter.getSerie(serie);
			// s.setEstado("activo");
			serieServive.saveSerie(s);
			

		}
		return resJoson;
	}

	//***************************SOLO PARA TEST***************************************

	// @PostMapping(value = "/saveSerie2", produces = { MediaType.APPLICATION_JSON_VALUE })
	// @ResponseBody
	// public String save2( ) {
	// 	Serie s=new Serie();

	// 	s.setSerie("adasdasd");
	// 	s.setCorrelativoActual(1);
	// 	s.setInicio(1);
	// 	s.setFin(100);

	// 	if(serieServive.saveSerie(s)){
	// 		return "registro guardado";
	// 	}else{
	// 		return "registro no guardado";
	// 	}
	// }


	@GetMapping(value = "/saveSerieGet", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String saveGet() {
		Serie s=new Serie();

		s.setSerie("ad");
		//s.setCorrelativoActual(1);
		//s.setInicio(1);
		s.setFin(100);

		if(serieServive.saveSerie(s)){
			return "registro guardado";
		}else{
			return "registro no guardado";
		}
	}
}
