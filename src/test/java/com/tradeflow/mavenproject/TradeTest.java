package com.tradeflow.mavenproject;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TradeTest {
	Trade t = new Trade("T-12",2,"CP-5","B-4","30/5/2022","30/4/2022","N");
	@Test
	public void testSetTrade_id() {
		t.setTrade_id("T-12");
		Assert.assertTrue(t.getTrade_id()=="T-12");
	}

	@Test
	public void testSetversion() {
		t.setversion(2);
		Assert.assertTrue(t.getVersion()==2);
	}

	@Test
	public void testSetCpt_id() {
		t.setCpt_id("CP-5");
		Assert.assertTrue(t.getCpt_id()=="CP-5");
	}

	@Test
	public void testSetBook_id() {
		t.setBook_id("B-4");
		Assert.assertTrue(t.getBook_id()=="B-4");
	}

	@Test
	public void testSetMaturity_date() {
		t.setMaturity_date("30/05/2022");
		Assert.assertTrue(t.getMaturity_date()=="30/05/2022");
	}

	@Test
	public void testSetCreated_date() {
		t.setCreated_date("30/04/2022");
		Assert.assertTrue(t.getCreated_date()=="30/04/2022");
	}

	@Test
	public void testSetExp_flag() {
		t.setExp_flag("N");
		Assert.assertTrue(t.getExp_flag()=="N");
	}

}
