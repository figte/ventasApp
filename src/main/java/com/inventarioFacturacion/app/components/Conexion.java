package com.inventarioFacturacion.app.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class Conexion{
 
    private String usuario;
    private String clave;
    private String url;    
    private Connection conn = null;
    
    public Conexion() throws Exception {             
        String server;
        String port;
        String database;    
        usuario = "postgres";
        clave = "admin";
        server = "localhost";
        port = "5432";
        database = "ferreteria";
        url ="jdbc:postgresql://localhost:5432/ferreteria";
    }

    public synchronized void conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url,usuario,clave);
            System.out.println("conexion establecida");
        } catch (ClassNotFoundException  | SQLException err) {
            System.out.println("Error " + err.getMessage());
        }                
    }
    
    public Connection getConexion() throws SQLException{
        System.out.println("Conexion retornada...."+conn.getSchema().toString());
        return conn;
    }
    
    public synchronized void desconectar() {
        try {
                conn.close();

                System.out.println("Conexion cerrada....");
        } catch (SQLException err) {
            System.out.println("Error " + err.getMessage());
        }
    }  
}
