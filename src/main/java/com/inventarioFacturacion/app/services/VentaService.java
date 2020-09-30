package com.inventarioFacturacion.app.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import com.inventarioFacturacion.app.components.Conexion;
import com.inventarioFacturacion.app.dao.IDetalleVenta;
import com.inventarioFacturacion.app.dao.IProducto;
import com.inventarioFacturacion.app.dao.ISeries;
import com.inventarioFacturacion.app.dao.IVenta;
import com.inventarioFacturacion.app.model.entity.DetalleVenta;
import com.inventarioFacturacion.app.model.entity.Producto;
import com.inventarioFacturacion.app.model.entity.Serie;
import com.inventarioFacturacion.app.model.entity.Venta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * VentaService
 */
@Service
public class VentaService {

    @Autowired
    IVenta rVenta;

    @Autowired
    IDetalleVenta rdetalleVenta;

    @Autowired
    IProducto rProducto;

    @Autowired
    ISeries rSerie;

    @Autowired
    Conexion conexion;

    public Serie getSerie(Long id) {
        return rSerie.findById(id).get();
    }

    // Listar Ventas
    @Transactional
    public List<Venta> getAllVenta() {
        return (List<Venta>) rVenta.findAll();
    }

    // Guardar Venta
    public Venta saveOrUpdate(Venta entity) {

        try {

            return rVenta.save(entity);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Error " + e.getMessage());
            return null;
        }
    }

    // Eliminar Venta
    public Boolean delete(Venta entity) {

        try {
            rVenta.delete(entity);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Error " + e.getMessage());
            return false;
        }
    }

    public Producto getProducto(String id) {
        return rProducto.findById(id).get();
    }

    public List<Producto> getAllProducto() {
        return (List<Producto>) rProducto.findAll();
    }

    public Boolean actualizarCorrelativo(Serie entity) {
        if (entity.getCorrelativoActual() == entity.getFin()) {
            entity.setEstado("finalizado");
        } else {
            entity.setCorrelativoActual(entity.getCorrelativoActual() + 1);
        }

        try {
            rSerie.save(entity);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public Boolean actualizarExistencias(List<DetalleVenta> detalles) {
        try {

            for (DetalleVenta detalleVenta : detalles) {
                Producto producto = detalleVenta.getProducto();
                Integer existenciasActuales = producto.getExistencias() - detalleVenta.getCantidad();
                producto.setExistencias(existenciasActuales);
                rProducto.save(producto);
            }

            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public JasperPrint exportReport(Map<String, Object> parameters)
            throws FileNotFoundException, JRException, SQLException {
       // String path = "C:\\Users\\FIGTE\\Desktop";
      //  List<Employee> employees = repository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:factura.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
       // JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Map<String, Object> p = parameters;
        
        // JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Invoice"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, p,conexion.getConexion());

        // if (reportFormat.equalsIgnoreCase("html")) {
        //     JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        // }
        // if (reportFormat.equalsIgnoreCase("pdf")) {
        //     JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
        // }

        // JasperViewer ver=new JasperViewer(jasperPrint,false );
        // ver.setTitle("Fatura");
        // ver.setVisible(true);
        // ver.setFocusable(true);


        return jasperPrint ;
    }
   
}