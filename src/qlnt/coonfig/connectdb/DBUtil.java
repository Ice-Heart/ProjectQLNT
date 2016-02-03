/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnt.coonfig.connectdb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lehoang
 */
public class DBUtil {
    public static void main(String[] args) {
        connectDB();
    }
    
    public static Connection connectDB(){
        Connection conn = null;
        
        String dbURL = "jdbc:sqlserver://localhost\\LEHOANG;databaseName=QLNT";
        String user = "sa";
        String pass = "lehoang0211@95";
        
        try {
            conn = DriverManager.getConnection(dbURL, user, pass);
            DatabaseMetaData dmd = conn.getMetaData();
            System.out.println("Driver name: " + dmd.getDriverName());
            System.out.println("Driver version: " + dmd.getDriverVersion());
            System.out.println("Product name: " + dmd.getDatabaseProductName());
            System.out.println("Product version: " + dmd.getDatabaseProductVersion());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return conn;
    }
}
