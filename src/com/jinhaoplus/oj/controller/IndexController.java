package com.jinhaoplus.oj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.User;

@Controller
public class IndexController {
	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response,User user) {
		CommonMessage message = new CommonMessage("200","To Login");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject("some", "jinhaoluo");
		modelAndView.addObject("message", message);
		HttpSession session = request.getSession();
		session.setAttribute("name", user.getUsername());
		System.out.println(session);
		System.out.println(session.getId());
		return modelAndView;
	}
}
