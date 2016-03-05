package com.jinhaoplus.oj.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.domain.User;
import com.jinhaoplus.oj.service.CoreDispatcherService;
import com.jinhaoplus.oj.service.ProblemsService;

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
		modelAndView.addObject("problemLanguages", Arrays.asList(problem.getProblemLanguage().split("&")));
		List<String> list =  Arrays.asList(problem.getProblemLanguage().split("&"));
		List<ProblemTest> displayTests = problemsService.getDisplayTestByProblemId(Integer.parseInt(problemId));
		modelAndView.addObject("displayTests",displayTests);
		return modelAndView;
	}
	
	@RequestMapping(value="/submitCode")
	@ResponseBody
	public List<ProblemTestResult> submitCode(HttpServletRequest request,HttpServletResponse response,ProblemSolution solution){
		coreDispatcherService.dispatchCore(solution);
		String userName = ((User)request.getSession().getAttribute("loginuser")).getUsername();
		String sourceWaitPath = request.getRealPath("")+"sourceWait/";
		solution.setSolutionCoder(userName);
		List<ProblemTestResult> results = coreDispatcherService.workFlow(solution,sourceWaitPath);
		return results;
	}
}
