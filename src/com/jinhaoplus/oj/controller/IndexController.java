package com.jinhaoplus.oj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.User;
import com.jinhaoplus.oj.service.ProblemsService;

@Controller
public class IndexController {
	@Autowired
	private ProblemsService problemsService;
	
	public void setProblemsService(ProblemsService problemsService) {
		this.problemsService = problemsService;
	}

	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		CommonMessage message = new CommonMessage("200","To Login","");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		HttpSession session = request.getSession();
		modelAndView.addObject("username", ((User)session.getAttribute("loginuser")).getUsername());
		modelAndView.addObject("problemsList", problemsService.getAllProblems());
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	@RequestMapping(value="/mySubmissions")
	@ResponseBody
	public ModelAndView checkMySubs(HttpServletRequest request,HttpServletResponse response){
		User user = (User) request.getSession().getAttribute("loginuser");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("my-submissions");
		List<ProblemSolution> solutions = problemsService.getAllSolutions(user.getUserid());
		modelAndView.addObject("mySolutions", solutions);
		return modelAndView;
	}
}
