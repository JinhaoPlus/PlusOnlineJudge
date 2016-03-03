package com.jinhaoplus.oj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.User;
import com.jinhaoplus.oj.service.ProblemsService;

import sun.print.resources.serviceui;

import com.jinhaoplus.oj.service.langCore.CoreDispatcherService;

@Controller
@RequestMapping(value="/problems")
public class ProblemsController {
	@Autowired
	private ProblemsService problemsService;
	
	public void setProblemsService(ProblemsService problemsService) {
		this.problemsService = problemsService;
	}
	
	@Autowired
	private CoreDispatcherService coreDispatcherService;

	public void setCoreDispatcherService(CoreDispatcherService coreDispatcherService) {
		this.coreDispatcherService = coreDispatcherService;
	}

	@RequestMapping(value="/{problemid}")
	public ModelAndView chooseProblem(HttpServletRequest request,HttpServletResponse response,@PathVariable("problemid") String problemId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("detail");
		Problem problem = problemsService.getById(Integer.parseInt(problemId));
		modelAndView.addObject("chosenProblem", problem);
		return modelAndView;
	}
	
	@RequestMapping(value="/submitCode")
	@ResponseBody
	public String submitCode(HttpServletRequest request,HttpServletResponse response,ProblemSolution solution){
		coreDispatcherService.dispatchCore(solution);
		String userName = ((User)request.getSession().getAttribute("loginuser")).getUsername();
		System.out.println("##"+request.getRealPath(""));
		String sourceWaitPath = request.getRealPath("")+"sourceWait/";
		solution.setSolutionCoder(userName);
		coreDispatcherService.workFlow(solution,sourceWaitPath);
		return "success";
	}
}
