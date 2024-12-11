/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConectionDB;

/**
 *
 * @author ROURY
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import popups.Popups;
import sc.pkgProject_Lenjuage_BD.Project_Lenjuage_BD;

public class ConectionDB {

    Popups popups = new Popups();
    private static final String URL = "jdbc:oracle:thin:@//158.23.48.83:1521/orcl";
    private static final String USER = "LBDAdmin";
    private static final String PASSWORD = "LBDPass123";
    private Connection connection;

    public ConectionDB() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Project_Lenjuage_BD.EstadoDB = "Conectado";
        } catch (SQLException e) {
            Project_Lenjuage_BD.EstadoDB = "NO Conectado: " + e.getMessage();
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            popups.error("No se pudo establecer la conexión a la base de datos.");
        }
        return connection;
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                Project_Lenjuage_BD.EstadoDB = "Desconectado de Oracle";
            } catch (SQLException e) {
                Project_Lenjuage_BD.EstadoDB = "Error al cerrar conexión: " + e.getMessage();
                e.printStackTrace();
            }
        }
    }
}
