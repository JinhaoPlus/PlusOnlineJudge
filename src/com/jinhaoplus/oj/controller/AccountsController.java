package com.jinhaoplus.oj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.User;
import com.jinhaoplus.oj.service.AccountsService;

@Controller
@RequestMapping(value="/accounts")
public class AccountsController {
	
	@Autowired
	private AccountsService accountsService;
	public void setAccountsService(AccountsService accountsService) {
		this.accountsService = accountsService;
	}
	
	
	@RequestMapping(value="/tosignup")
	@ResponseBody
	public ModelAndView toSignUp(HttpServletRequest request,HttpServletResponse response,User user) {
		CommonMessage message = new CommonMessage("200","Signup Successful");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("signup");
		modelAndView.addObject("some", "jinhaoluo");
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/tologin")
	@ResponseBody
	public ModelAndView toLogin(HttpServletRequest request,HttpServletResponse response,User user) {
		CommonMessage message = new CommonMessage("200","To Login");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		request.getSession().setAttribute("user", user);
		modelAndView.addObject("some", "jinhaoluo");
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	
}
