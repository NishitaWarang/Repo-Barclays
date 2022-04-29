package com.tradeflow.mavenproject;

import java.sql.Date;

public class Trade {
	String trade_id;
	int version;
	String cpt_id;
	String book_id;
	String maturity_date;
	String created_date;
	String exp_flag;
	
	public Trade(String trade_id,int version, String cpt_id, String book_id, String maturity_date, String created_date, String exp_flag) {
		this.trade_id = trade_id;
		this.version = version;
		this.cpt_id = cpt_id;
		this.book_id = book_id;
		this.maturity_date = maturity_date;
		this.created_date = created_date;
		this.exp_flag = exp_flag;
	}
	
	public String getTrade_id() {
		return trade_id;
	}
	public int getVersion() {
		return version;
	}
	public String getCpt_id() {
		return cpt_id;
	}
	public String getBook_id() {
		return book_id;
	}
	public String getMaturity_date() {
		return maturity_date;
	}
	public String getCreated_date() {
		return created_date;
	}
	public String getExp_flag() {
		return exp_flag;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	public void setversion(int version) {
		this.version = version;
	}
	public void setCpt_id(String cpt_id) {
		this.cpt_id = cpt_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public void setMaturity_date(String maturity_date) {
		this.maturity_date = maturity_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public void setExp_flag(String exp_flag) {
		this.exp_flag = exp_flag;
	}
}
