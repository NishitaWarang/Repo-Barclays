package com.tradeflow.mavenproject;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import junit.framework.Assert;

public class AppTest {
	Trade t = new Trade("T-5",1,"Cp-3","B-5","20/06/2022","28/04/2022","N");
	@Mock
	Connection mockconn;
	
	@Mock
	Statement mockstmt;
	
	@Mock
	DbCalls mockdbCall;
	
	@Mock
	DbConnection mockdbConnection;

	@InjectMocks
	App app;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void initTest() {
		Mockito.when(mockdbConnection.connect()).thenReturn(null);
		Mockito.when(mockdbCall.createNewTable(mockconn)).thenReturn(1);
		Assert.assertEquals(null, mockconn);
		int value = mockdbCall.createNewTable(mockconn);
		Assert.assertEquals(value, 1);
		
	}
	
	@Test
	public void scanIncomingTradesTest() {
		//Mockito.doNothing().when(App.filterAndInsertTrade((Trade)Mockito.any()));
	//	app.scanIncomingTrades("incomingtrades.json");
	}
	@Test
	public void filterAndInsertTradeTest() {
		try {
			Mockito.when(mockconn.createStatement()).thenReturn(mockstmt);
			Mockito.when(mockconn.createStatement().executeUpdate(Mockito.anyString())).thenReturn(1);
			int value = mockconn.createStatement().executeUpdate("");
			Assert.assertEquals(value, 1);
			app.filterAndInsertTrade(t);
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}
}
