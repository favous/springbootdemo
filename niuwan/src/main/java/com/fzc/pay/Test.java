package com.fzc.pay;

import com.fzc.pay.dto.PayQueryDto;
import com.fzc.pay.dto.PaySubmitDto;
import com.fzc.pay.service.PayService;
import com.fzc.pay.service.impl.PayServiceImpl;

public class Test {
	
	private static String RETURN_URL = "/pay/callbackFront";
	private static String NOTIFY_URL = "/pay/callbackBlack";

	public static void main(String[] args) throws Exception {
		
		String amount = "111";
		String out_trade_no = "123";
		String subject = "remart it";
		String pay_type = "2";
		String currency = "1";
		
		PayService pay = new PayServiceImpl();
		
		PaySubmitDto submitDto = new PaySubmitDto(amount, out_trade_no, subject, pay_type, currency, RETURN_URL, NOTIFY_URL);
		String str = pay.submit(submitDto);
		System.out.println(str);

		PayQueryDto queryDto = new PayQueryDto("2018120222001462901019701494", "0.01", "271ec7a7-3d04-4d5c-9656-2cd3ea07adc269771");
		boolean result = pay.check(queryDto);
		System.out.println(result);
	}

}
