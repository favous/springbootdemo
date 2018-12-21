package com.fzc.pay.dto;

public class PayQueryDto {
	private String trade_no;
	private String amount;
	private String out_trade_no;

	public PayQueryDto() {
		super();
	}

	public PayQueryDto(String trade_no, String amount, String out_trade_no) {
		super();
		this.trade_no = trade_no;
		this.amount = amount;
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

}