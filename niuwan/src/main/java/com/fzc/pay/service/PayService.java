package com.fzc.pay.service;

import com.fzc.pay.dto.PayQueryDto;
import com.fzc.pay.dto.PaySubmitDto;

public interface PayService {

	String submit(PaySubmitDto submitDto);
	
	boolean check(PayQueryDto queryDto);
}
