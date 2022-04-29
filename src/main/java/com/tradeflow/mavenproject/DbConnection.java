package com.tradeflow.mavenproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	public Connection connect() {  
        Connection conn = null;  
        try {  
            String url = "jdbc:sqlite:trades.db"; 
       //   Class.forName("java.sql.Driver");
            conn = DriverManager.getConnection(url);        
            System.out.println("Connection to SQLite has been established.");   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }   
        return conn;  
    }  
}
