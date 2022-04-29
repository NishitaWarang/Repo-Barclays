package com.tradeflow.mavenproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCalls {
	Statement stmt = null;
	public int createNewTable(Connection conn) {
		String dropTable = "DROP TABLE IF EXISTS store";
        String createTable = "CREATE TABLE IF NOT EXISTS store(\n"
            	+	"trade_id TEXT NOT NULL,\n"
            	+	"version INTEGER NOT NULL,\n"
            	+   "cpt_id TEXT NOT NULL,\n"
            	+	"book_id TEXT NOT NULL,\n"
            	+	"maturity_date TEXT NOT NULL,\n"
            	+	"created_date TEXT NOT NULL,\n"
            	+	"exp_flag INTEGER NOT NULL\n"
            	+	")";
            
    		try {
    			if(conn!=null) {
    			stmt = conn.createStatement();
    			System.out.println("Connection is open");
    			stmt.execute(dropTable);
    			stmt.execute(createTable);
    			}
    			else
    				System.out.println("Closed connection");
    		} catch (SQLException e1) {
    			e1.printStackTrace();
    		}  
            System.out.println("Table Created");
            return 1;
	}

}
