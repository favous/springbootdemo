
package com.gyumaru.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GyumaruController {

	// 首页
	@RequestMapping("gyumaru")
	public String articledtl(Model model, String idfa, String unique_device_id, HttpSession session, HttpServletResponse response) throws IOException {
		return "redirect:/dist/index.html?uid=" + unique_device_id + "&idfa=" + idfa;// 跳转
	}
}