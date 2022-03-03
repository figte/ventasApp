package com.inventarioFacturacion.app.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class Conexion {

    private String usuario;
    private String clave;
    private String url;
    private Connection conn = null;

    public Conexion() throws Exception {
        String server;
        String port;
        String database;
        usuario = "qgknuclpekszbd";
        clave = "298810ac684981cfdae6df5c108907eef565665e5a6fc7cfa8b855b5259aec50";
        // server = "localhost";
        // port = "5432";
        // database = "ferreteria";
        url = "jdbc:postgresql://ec2-34-235-62-201.compute-1.amazonaws.com:5432/d12egk19ht9hb";
    }

    public synchronized void conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, usuario, clave);
            System.out.println("conexion establecida");
        } catch (ClassNotFoundException | SQLException err) {
            System.out.println("Error " + err.getMessage());
        }
    }

    public Connection getConexion() throws SQLException {
        System.out.println("Conexion retornada...." + conn.getSchema().toString());
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
