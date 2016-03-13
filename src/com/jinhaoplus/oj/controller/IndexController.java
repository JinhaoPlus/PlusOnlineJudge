package com.jinhaoplus.oj.controller;

import java.util.List;
import java.util.Random;

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
		modelAndView.addObject("loginuser", ((User)session.getAttribute("loginuser")));
		modelAndView.addObject("problemsList", problemsService.getAllProblems());
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	@RequestMapping(value="/try")
	public void tryOne(HttpServletRequest request,HttpServletResponse response) {
		int problemNum = problemsService.getAllProblems().size();
		int tryProblemId = new Random().nextInt(problemNum);
		try {
			request.getRequestDispatcher("/problems/"+tryProblemId).forward(request, response);;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/about")
	public ModelAndView aboutPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("about");
		return modelAndView;
	}
	
	@RequestMapping(value="/mySubmissions")
	@ResponseBody
	public ModelAndView checkMySubs(HttpServletRequest request,HttpServletResponse response){
		User user = (User) request.getSession().getAttribute("loginuser");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("my-submissions");
		List<ProblemSolution> solutions = problemsService.getMySolutions(user.getUserid());
		modelAndView.addObject("submitTimes", solutions.size());
		modelAndView.addObject("solutions", solutions);
		return modelAndView;
	}
}
