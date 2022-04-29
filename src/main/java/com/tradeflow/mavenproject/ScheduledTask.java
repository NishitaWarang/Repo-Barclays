package com.tradeflow.mavenproject;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.TimerTask;

public class ScheduledTask extends TimerTask{
	Date now; 
	int i;
	@Override
	public void run() {
	//	now = new Date(); // initialize date
	//	System.out.println("Time is :" + now);
		String setExpiredTrades = "update store set exp_flag = \""+"Y"+"\" where maturity_date < \""+new Date()+"\"";
		try {
			Statement stmt = App.conn.createStatement();
			 i = stmt.executeUpdate(setExpiredTrades);
			System.out.println("Updated expired trades "+i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	


}
