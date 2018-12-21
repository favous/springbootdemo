package com.fzc.pay.dto;

public class PaybackDto {
	private String trade_no;
	private String trade_status;
	private String amount;
	private String currency;
	private String out_trade_no;
	
	public PaybackDto(String trade_no, String trade_status, String amount, String currency, String out_trade_no) {
		super();
		this.trade_no = trade_no;
		this.trade_status = trade_status;
		this.amount = amount;
		this.currency = currency;
		this.out_trade_no = out_trade_no;
	}
	
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
}
