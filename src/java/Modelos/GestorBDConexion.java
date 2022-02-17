/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author German
 */
public class GestorBDConexion {

    private Connection con;

    public Connection getConexion() {
        return con;
    }

    public GestorBDConexion() {

    }

    public void AbrirConexion() {
        try {

//          **********************************AQUÍ********************************************
            String url = "jdbc:sqlserver://GERMAN:1433;databaseName=UTNAcademia";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

//          **********************************AQUÍ********************************************
            con = DriverManager.getConnection(url, "sa", "123");

            System.out.println("Conexión a la BD");

        } catch (Exception e) {
            System.out.println("Error en conexión ");
        }
    }

    public void CerrarConexion() {
        try {
            con.close();
            System.out.println("Conexión cerrada");

        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión");
        }
    }
}
