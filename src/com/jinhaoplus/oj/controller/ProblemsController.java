package com.jinhaoplus.oj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.service.ProblemsService;

@Controller
@RequestMapping(value="/problems")
public class ProblemsController {
	@Autowired
	private ProblemsService problemsService;
	
	public void setProblemsService(ProblemsService problemsService) {
		this.problemsService = problemsService;
	}

	@RequestMapping(value="/{problemid}")
	public ModelAndView chooseProblem(HttpServletRequest request,HttpServletResponse response,@PathVariable("problemid") String problemId){
		System.out.println(problemId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("detail");
		Problem problem = problemsService.getById(Integer.parseInt(problemId));
		System.out.println(problem);
		modelAndView.addObject("chosenProblem", problem);
		return modelAndView;
	}
}
