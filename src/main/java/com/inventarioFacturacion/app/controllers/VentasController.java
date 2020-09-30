package com.inventarioFacturacion.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.inventarioFacturacion.app.components.Conexion;
import com.inventarioFacturacion.app.components.ResponseJson;
import com.inventarioFacturacion.app.model.dto.VentaDto;
import com.inventarioFacturacion.app.model.entity.DetalleVenta;
import com.inventarioFacturacion.app.model.entity.Sucursal;
import com.inventarioFacturacion.app.model.entity.Venta;
import com.inventarioFacturacion.app.services.VentaService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;


@Controller
@RequestMapping("ventas")
public class VentasController {
	
	 //listado en memoria de detalles de la venta a guardar
	 public static List<DetalleVenta> detalles=new ArrayList<DetalleVenta>();

	 @Autowired
	 private ResponseJson resJoson;

	@Autowired
	VentaService ventaService;

	@GetMapping(value = "/facturar")
	public String listar(Model model) {
		model.addAttribute("productos", ventaService.getAllProducto());
		
		detalles=new ArrayList<DetalleVenta>();
		return "views/Ventas/ventas";
	}

	@GetMapping(value="all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Venta> getAll() {
		return ventaService.getAllVenta();
	}

	@PostMapping(value="agregarDetalle")
	@ResponseBody
	// @CrossOrigin
    public Object agregarDetalle(@RequestParam Integer cantidad, 
								  @RequestParam String idProducto,
								  @RequestParam String tipoDetalle) {
	   HashMap<String, String> json=new HashMap<>();
	   try {   
		Boolean producto_repetido=false;
		//evalauando si el producto ya ha sido agregado
		if(detalles.size()>0){
			for (DetalleVenta detalleVenta : detalles) {
				//evaluando si hay un producto repetido
				if(detalleVenta.getProducto().getCodigo().equals(idProducto)){
					producto_repetido=true;  //se cambia ele estado porque el producto estaba agregado
						detalleVenta.setCantidad(detalleVenta.getCantidad()+cantidad); //se incrementan las existencias
						
				}
			}
		}

		if(producto_repetido==false){
			DetalleVenta dv=new DetalleVenta();
			dv.setCantidad(cantidad);
			dv.setProducto(ventaService.getProducto(idProducto)); 
			dv.setTipo_detalle(tipoDetalle);

			detalles.add(dv);
		}
		

		json.put("mensaje", "Detalle agregado");
		return resJoson;
	   } catch (Exception e) {
		   //TODO: handle exception
		   json.put("mensaje", "Error: "+e.getMessage());
		   return resJoson;
	   }
	}
	
	
    @GetMapping(value="allDetalles",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DetalleVenta> getDetallesMemoria() {
	   if(detalles.size()>0)
		return detalles;
		else
		return null;

	}

	@GetMapping(value="eliminarDetalle/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean eliminarDetalle(@PathVariable Integer id) {
		
		try {
			DetalleVenta dv= detalles.get(id);
			detalles.remove(dv);
			return true;
		} catch (Exception e) {
			//TODO: handle exception
			return false;
		}

	}


	
	@PostMapping(value="save")
    @ResponseBody
    public ResponseJson save(@ModelAttribute @Valid VentaDto venta, BindingResult result) {
        String mensaje="";
		resJoson = resJoson.validateJson(result);
			
			if(detalles.size()==0){
				resJoson.getErrorMessages().put("table-factura", "*No ha seleccionado de detalles para la venta");
				resJoson.setValidated(false);
			}
			for (DetalleVenta detalleVenta : detalles) {
				//validando existencia del producto
				if(detalleVenta.getProducto().getExistencias()<detalleVenta.getCantidad()){
					resJoson.setValidated(false);
					mensaje=mensaje+" *no hay suficientes existencias del producto "+detalleVenta.getProducto().getNombre();
				}

			}

			if(!mensaje.equals("")){
				resJoson.getErrorMessages().put("table-factura", mensaje);
			}

			if(resJoson.isValidated() == true) {

			Venta entity=new Venta();
			entity.setCliente(venta.getCliente());
			entity.setFecha(venta.getFecha());
			//entity.setCliente(cliente);
			entity.setObservacion(venta.getObservacion());
			entity.setTipoDocumento(venta.getTipoDocumento());
			entity.setSumas(venta.getSumas());
			entity.setIva(venta.getIva());
			entity.setCesc(venta.getCesc());
			entity.setExento(venta.getExento());
			entity.setNoSujeto(venta.getNoSujeto());
			entity.setTotal(venta.getTotal());
			entity.setCorrelativo(ventaService.getSerie(venta.getIdSerie()));					

			for (DetalleVenta detalleVenta : detalles) {
				detalleVenta.setVenta(entity);
			}
		
			entity.setDetallesVentas(detalles);
			try {
				Venta v=ventaService.saveOrUpdate(entity);
				ventaService.actualizarCorrelativo(entity.getCorrelativo());
				ventaService.actualizarExistencias(detalles);
				

				Map<String,String> m=new HashMap<String,String>();
				m.put("idVenta", v.getId().toString());

				resJoson.setErrorMessages(m);
				
				//return true;  
			} catch (Exception e) {
				//TODO: handle exception
				//return false;
			}
		}

		return resJoson;
	}	
	
	
	@GetMapping("/report/{idVenta}")
	public void getAllAttentionsByStatusAndUserAndDate(HttpServletResponse response,@PathVariable Integer idVenta)
								throws IllegalAccessException {
	try {
		   // InputStream inputStream = this.getClass().getResourceAsStream("/factura.jrxml");
			// BufferedImage image = ImageIO.read(getClass().getResource("/reports/logo.png"));
		//	File file = new ClassPathResource("factura.jrxml").getFile();
		//	InputStream is = new ClassPathResource("factura.xml").getInputStream();
			InputStream inputStream = this.getClass().getResourceAsStream("/factura.jasper");

			Map<String,Object> parametros=new HashMap<String,Object>();
			parametros.put("idVenta", idVenta);

			Conexion conexion=new Conexion();
			conexion.conectar();

			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion.getConexion());
			
			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
			pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
			pdfExporter.exportReport();
			response.setContentType("application/pdf");
			response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
			response.setHeader("Content-disposition", "inline; filename=factura.pdf");
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(pdfReportStream.toByteArray());
			responseOutputStream.close();
			pdfReportStream.close();
			conexion.desconectar();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
