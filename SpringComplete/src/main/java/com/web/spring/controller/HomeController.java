package com.web.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.spring.config.encode.AES;
import com.web.spring.mapper.UserMapper;

@Controller
public class HomeController {
	private String secrectKey = "Aa@1234";
	@Autowired
	UserMapper userMapper;

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		
		/*
		 * System.out.println(AES.encrypt("jdbc:mysql://127.0.0.1:3306/login",
		 * secrectKey)); System.out.println(AES.encrypt("root", secrectKey));
		 * System.out.println(AES.encrypt("1234", secrectKey));
		 */
		return mav;
	}
}
