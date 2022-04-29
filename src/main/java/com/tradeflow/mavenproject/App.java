package com.tradeflow.mavenproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Timer;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class App 
{
	public static Connection conn = null;
	public static DbCalls dbCall = new DbCalls();
	static Statement stmt=null;
	public static DbConnection dbConnection = new DbConnection();
    public static void main( String[] args ) throws SQLException, InterruptedException
    {
       init();
    /*   Timer time = new Timer();
		ScheduledTask st = new ScheduledTask();
		time.schedule(st, 0, 10000);*/
       scanIncomingTrades("incomingtrades.json");
     //  scanIncomingTrades("test.json");
    /*   Thread.sleep(10000);
       st.cancel();*/
       printTable();  
       dbConnection.closeConnection(conn);
    }
	
	 public static void filterAndInsertTrade(Trade t) throws SQLException, java.text.ParseException { 
	    Date maturity_date=new SimpleDateFormat("dd/MM/yyyy").parse(t.getMaturity_date());
 //       Date created_date=new SimpleDateFormat("dd/MM/yyyy").parse(t.getCreated_date());
		boolean valid = true;
		boolean equalVer = false; 
		String updateTrade,insertTrade;
		String validation1 = "select count(*) as total from store where trade_id = \""+t.getTrade_id()+
								"\" and version > " + t.getVersion(); 
	//	System.out.println(validation1);
		stmt = conn.createStatement();
		  ResultSet rs1 = stmt.executeQuery(validation1);
		  int count1 = Integer.parseInt(rs1.getString(1));
		  if(count1!=0) { 
			  valid = false; 
		  } 
		  else {
			  String validation2 = "select count(*) as total from store where trade_id = \""+t.getTrade_id()+
					  				"\" and version = " + t.getVersion(); 
			  stmt = conn.createStatement();
			  ResultSet rs2 = stmt.executeQuery(validation2);
			  int count2 = Integer.parseInt(rs2.getString(1));
			  if(count2!=0) { 
				  equalVer = true; 
			  }
		  }
		  if(maturity_date.compareTo(new Date())<0) {
			  valid = false; 
		  }
		  if(valid) {
			  if(equalVer) { 
			  updateTrade = "update store \n"+
			  "set cpt_id = \""+t.getCpt_id()+"\",\n"+
			  "book_id = \""+t.getBook_id()+"\",\n"+
			  "maturity_date = \""+t.getMaturity_date()+"\",\n"+
			  "created_date = \""+t.getCreated_date()+"\",\n"+
			  "exp_flag = \""+t.getExp_flag()+"\"\n"+
			  "where trade_id = \""+t.getTrade_id()+"\"\n"+
			  "and version = \""+t.getVersion()+"\"";
			  stmt = conn.createStatement();
			  stmt.executeUpdate(updateTrade);
			  } 
			  else { 
				  insertTrade = "insert into store(trade_id,version,cpt_id,book_id,maturity_date,created_date,exp_flag) \n"+
						  		  "values(\""+t.getTrade_id()+"\", \"" +t.getVersion()+"\", \""+t.getCpt_id()+"\", \""+t.getBook_id()+
						  		  "\", \""+t.getMaturity_date()+"\", \""+t.getCreated_date()+"\", \""+t.getExp_flag()+"\")";
				  stmt = conn.createStatement();
				  stmt.executeUpdate(insertTrade);
			  } 
		  }
	}
		  
	  
	 
	  public static void init() {
		 	conn = dbConnection.connect();
		 	dbCall.createNewTable(conn);
	  }
	  public static void printTable() throws SQLException {
		  stmt= conn.createStatement();
				 	ResultSet rs = stmt.executeQuery("select * from store");
				    ResultSetMetaData rsmd = rs.getMetaData();
				    System.out.println("SELECT * FROM store");
				    int columnsNumber = rsmd.getColumnCount();
				    int k=0;
				    while (rs.next()) {
				    	k++;
				        for (int i = 1; i <= columnsNumber; i++) {
				            if (i > 1) System.out.print(",  ");
				            String columnValue = rs.getString(i);
				            System.out.print(columnValue + " " + rsmd.getColumnName(i));
				        }
				        System.out.println("");
				    }
				    System.out.println("Count of rows = "+k);
	  }
				    
	  public static void scanIncomingTrades(String fileName) {
		 JSONParser parser = new JSONParser();
		 try (FileReader reader = new FileReader(fileName))
	        {
	            //Read JSON file
	            Object obj = parser.parse(reader);
	 
	            JSONArray tradeList = (JSONArray) obj;
	            System.out.println(tradeList);
	             
	            Gson gson = new Gson(); 
	            
	            Type tradeListType = new TypeToken<ArrayList<Trade>>(){}.getType();
	             
	            ArrayList<Trade> tradeArray = gson.fromJson(tradeList.toJSONString(), tradeListType);  
	             
	            for(Trade trade : tradeArray) { 	
	              filterAndInsertTrade(trade);
	            }
		     } catch(Exception e) {
		          e.printStackTrace();
		     }
	    } 
}
	 


