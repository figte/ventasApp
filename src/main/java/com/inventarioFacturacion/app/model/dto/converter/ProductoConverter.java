/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.model.dto.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.dto.ProductoDto;
import com.inventarioFacturacion.app.model.entity.Entradas;
import com.inventarioFacturacion.app.model.entity.Producto;
import com.inventarioFacturacion.app.model.entity.Sucursal;
// TODO: Auto-generated Javadoc
import com.inventarioFacturacion.app.services.ProductoService;

/**
 * Componente encargado de convertir manualmente a formato Json.
 *
 * @Jaime_Ramírez 19-9-2019
 */

@Component
public class ProductoConverter {
	
	@Autowired
	ProductoService productoService;
	/**
	 * Lista sucursal.
	 *
	 * @param lista the lista
	 * @return the list
	 */
	
	public List<Map<String, String>> listaProductosByCategoria(List<Producto> lista) {

		List<Map<String, String>> listOfMaps = new ArrayList<>();

		Map<String, String> map = null;
		try {
			for (Producto element : lista) {
				map = new HashMap<String, String>();
				map.put("codigo", element.getCodigo());
				map.put("nombre", element.getNombre());
				listOfMaps.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ProcessingEntityException("Error al procesar el usuario");
		}

		return listOfMaps;
	}

	public List<Map<String, String>> listaProductos(List<Producto> lista) {

			List<Map<String, String>> listOfMaps = new ArrayList<Map<String, String>>();

			Map<String, String> map = null;
			try {
				for (Producto item : lista) {
					map = new HashMap<String, String>();

					map.put("correlativo", item.getCodigo());
					map.put("nombre", item.getNombre());
					map.put("precio",item.getPrecio().toString());
					map.put("categoria",item.getCategoria().getNombre());
					map.put("proveedor",item.getProveedor().getNombre());
					map.put("existencias",item.getExistencias().toString());
					map.put("stock",item.getStock());	
					map.put("unidadDeMedida",item.getUnidadDeMedida());	
					map.put("estado",item.getEstado().toString());	

					try {
						if(item.getImpuesto_CESC()==true){
							map.put("impuesto_CESC","SI");
						}else{
							if(item.getImpuesto_CESC()==false)
								map.put("impuesto_CESC","NO");
							else
								map.put("impuesto_CESC","-");	
						}
					} catch (Exception e) {
						
						map.put("impuesto_CESC","-");
					}
					
					map.put("editar",
							"<button type='button' class='btn btn-outline-success openEdit'><i class='fa fa-edit'></i> </button>");
					map.put("eliminar",
							"<button type='button' class='btn btn-outline-danger openDelete'><i class='fa fa-minus'></i> </button>");
					listOfMaps.add(map);
				}

			} catch (Exception e) {
				throw new ProcessingEntityException("Error al procesar..."+e.getMessage());
			}
			return listOfMaps;
		}

		public Producto getProducto(ProductoDto dto){
			Producto entity=new Producto();
				try {
					entity.setCodigo(dto.getCodigo());
					entity.setNombre(dto.getNombre());
					entity.setProveedor(productoService.getProveedor(dto.getIdProveedor()));
					entity.setExistencias(dto.getExistencias());
					entity.setStock(dto.getStock());
					entity.setUnidadDeMedida(dto.getUnidadDeMedida());
					entity.setEstado(dto.getEstado());
					entity.setPrecio(dto.getPrecio());
					entity.setCategoria(productoService.getCategoria(dto.getIdCategoria()));
					if(dto.getImpuesto_CESC()==1)
						entity.setImpuesto_CESC(true);
					
					if(dto.getImpuesto_CESC()==0)
						entity.setImpuesto_CESC(false);

					return entity;
				} catch (Exception e) {
					System.err.println("Error: "+e.getMessage());
					return null;
				}
		}


}