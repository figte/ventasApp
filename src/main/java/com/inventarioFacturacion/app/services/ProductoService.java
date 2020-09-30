package com.inventarioFacturacion.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventarioFacturacion.app.dao.ICategoria;
import com.inventarioFacturacion.app.dao.IProducto;
import com.inventarioFacturacion.app.dao.IProveedor;
import com.inventarioFacturacion.app.exceptions.ProcessingEntityException;
import com.inventarioFacturacion.app.model.entity.Categoria;
import com.inventarioFacturacion.app.model.entity.Producto;
import com.inventarioFacturacion.app.model.entity.Proveedor;
@Service
public class ProductoService {
	@Autowired
	private IProducto iproducto;
	@Autowired
	private IProveedor iproveedor;
	@Autowired
	private ICategoria icategoria;

	
	@Transactional
	public List<Producto> lisProductoByCategoria(Long idCategoria) {
		List<Producto> list = null;
		try {
			list = iproducto.listarByCategoria(idCategoria);
		} catch (Exception e) {
			System.out.print(e);
			throw new ProcessingEntityException("Error al listar Productos");
		}
		return list;
	}

	public List<Producto> listProducto() {
		return (List<Producto>) iproducto.findAll();
	}

	public List<Proveedor> listProveedor() {
		return (List<Proveedor>) iproveedor.findAll();
	}
	public List<Categoria> listCategoria() {
		return (List<Categoria>) icategoria.findAll();
	}

	public Boolean saveOrUpdate(Producto entity){
		try {
			iproducto.save(entity);
			return true;
		} catch (Exception e) {
			//TODO: handle exception
			System.err.println("Error en el service: "+e.getMessage());
			return false;
		}
	}

	public Boolean delete(Producto entity){
		try {
			iproducto.delete(entity);
			return true;
		} catch (Exception e) {
			//TODO: handle exception
			return false;
		}
	}

	public Producto getProducto(String codigo){
		return iproducto.findById(codigo).get();
	}

	public Proveedor getProveedor(Long id){
		return iproveedor.findById(id).get();
	}
	
	public Categoria getCategoria(Long id){
		return icategoria.findById(id).get();
	}
}
