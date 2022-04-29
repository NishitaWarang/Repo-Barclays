package com.tradeflow.mavenproject;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AppTest {
	@Mock
	Connection conn;
	
	@Mock
	Statement stmt;
	
	@Mock
	DbCalls dbCall;

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
