package com.jinhaoplus.oj.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView toSignUp(HttpServletRequest request,HttpServletResponse response,User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("signup");
		modelAndView.addObject("some", "jinhaoluo");
		return modelAndView;
	}
	
	@RequestMapping(value="/signup")
	public ModelAndView signUp(HttpServletRequest request,HttpServletResponse response,User user) {
		ModelAndView modelAndView = new ModelAndView();
		CommonMessage message = accountsService.signUp(user);
		
		modelAndView.setViewName("signup-success");
		modelAndView.addObject("some", "jinhaoluo");
		return modelAndView;
	}
	
	@RequestMapping(value="/login")
	public void login(HttpServletRequest request,HttpServletResponse response,User user) throws IOException {
		CommonMessage message = accountsService.login(user);
		if(message.getCode().equals("200")){
			user = accountsService.getUserByName(user.getUsername());
			request.getSession().setAttribute("loginuser", user);
		}
		response.sendRedirect(request.getContextPath()+"/index");
	}
	
	@RequestMapping(value="/logout")
	public void loginout(HttpServletRequest request,HttpServletResponse response,User user) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/index");
	}
	
	@RequestMapping(value="/tologin")
	public ModelAndView toLogin(HttpServletRequest request,HttpServletResponse response,User user) {
		CommonMessage message = new CommonMessage("200","To Login","");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		request.getSession().setAttribute("user", user);
		modelAndView.addObject("some", "jinhaoluo");
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	
}
