package com.jinhaoplus.oj.controller;

import java.util.Arrays;
import java.util.Date;
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
import com.jinhaoplus.oj.util.Source2FileService;

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
		Problem problem = problemsService.getProblemById(Integer.parseInt(problemId));
		modelAndView.addObject("chosenProblem", problem);
		modelAndView.addObject("problemLanguages", Arrays.asList(problem.getProblemLanguage().split("&")));
		List<String> list =  Arrays.asList(problem.getProblemLanguage().split("&"));
		List<ProblemTest> displayTests = problemsService.getDisplayTestsByProblemId(Integer.parseInt(problemId));
		modelAndView.addObject("displayTests",displayTests);
		return modelAndView;
	}
	
	@RequestMapping(value="/submitCode")
	@ResponseBody
	public ModelAndView submitCode(HttpServletRequest request,HttpServletResponse response,ProblemSolution solution){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("solutionDetail");
		coreDispatcherService.dispatchSolution(solution);
		int userId = ((User)request.getSession().getAttribute("loginuser")).getUserid();
		//for Linux server and Mac
		String sourceWaitPath = request.getRealPath("")+"sourceWait/";
		//for Windows Server
//		String sourceWaitPath = request.getRealPath("")+"/sourceWait/";
		solution.setSolutionCoderId(userId);
		solution.setCodeSubmitTime(new Date());
		int solutionId = problemsService.insertSolution(solution);
		solution.setSolutionId(solutionId);
		List<ProblemTestResult> results = coreDispatcherService.workFlow(solution,sourceWaitPath);
		modelAndView.addObject("testResults",results);
		Problem problem = problemsService.getProblemById(solution.getProblemId());
		modelAndView.addObject("chosenProblem", problem);
		modelAndView.addObject("solution", solution);
		return modelAndView;
	}
	
	@RequestMapping(value="/getSolutionDetail/{problemId}/{solutionId}")
	public ModelAndView getSolutionDetail(HttpServletRequest request,HttpServletResponse response,@PathVariable("problemId") String problemId,@PathVariable("solutionId") String solutionId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("solutionDetail");
		Problem problem = problemsService.getProblemById(Integer.parseInt(problemId));
		modelAndView.addObject("chosenProblem", problem);
		ProblemSolution solution = problemsService.getSolutionById(Integer.parseInt(solutionId));
		solution = Source2FileService.sourceForACE(solution);
		modelAndView.addObject("solution", solution);
		List<ProblemTestResult> testResults = problemsService.getTestResultsBySolutionId(Integer.parseInt(solutionId));
		modelAndView.addObject("testResults",testResults);
		return modelAndView;
	}
	
}
