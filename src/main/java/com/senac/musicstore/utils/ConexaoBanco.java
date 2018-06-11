package com.senac.musicstore.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Magno Veras
 */
public class ConexaoBanco {
    
    public Connection createConnection(){
        String url = "jdbc:mysql://localhost:3306/projetointegradoriv";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, "root", "123456");
            System.out.println("conectado");
            
            return conn;
        } catch (SQLException e) {
            System.out.println("Deu ruim" +e);
        }catch (Exception e) {
              System.out.println("Deu ruim" +e);
        } 
            return null;
    } 
    

}
    
