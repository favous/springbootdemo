package com.fzc.pay.dto;

public class PaySubmitDto {
	private String amount;
	private String out_trade_no;
	private String subject;
	private String pay_type;
	private String currency;
	private String return_url;
	private String notify_url;

	public PaySubmitDto() {
		super();
	}

	public PaySubmitDto(String amount, String out_trade_no, String subject, String pay_type, String currency,
			String return_url, String notify_url) {
		super();
		this.amount = amount;
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.pay_type = pay_type;
		this.currency = currency;
		this.return_url = return_url;
		this.notify_url = notify_url;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
}