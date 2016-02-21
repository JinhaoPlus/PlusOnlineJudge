package com.jinhaoplus.oj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value="/signup")
	@ResponseBody
	public String signUp(HttpServletRequest request,HttpServletResponse response,User user) {
		String userName = request.getParameter("username");
		String idEmail = request.getParameter("id_email");
		String password = request.getParameter("password");
		System.out.println(user);
		return "success";
	}
}
