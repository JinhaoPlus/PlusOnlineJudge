package com.jinhaoplus.oj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.dao.ProblemsDao;
import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.service.ProblemsService;

@Service
public class ProblemsServiceImpl implements ProblemsService{

	@Autowired
	private ProblemsDao problemsDao;
	
	

	public void setProblemsDao(ProblemsDao problemsDao) {
		this.problemsDao = problemsDao;
	}



	@Override
	public List<Problem> getAllProblems() {
		List<Problem> problems = problemsDao.getAllProblems();
		return problems;
	} 



	@Override
	public Problem getProblemById(int id) {
		return problemsDao.getProblemById(id);
	}

	@Override
	public void updateProblem(Problem problem,ProblemSolution solution) {
		problem.setProblemSolveTimes(problem.getProblemSolveTimes()+1);
		if("AC".equals(solution.getFinalOJResult()))
			problem.setProblemAcTimes(problem.getProblemAcTimes()+1);
		problemsDao.updateProblem(problem);
	}


	@Override
	public List<ProblemTest> getTestsByProblemId(int problemId) {
		return problemsDao.getTestsByProblemId(problemId);
	}



	@Override
	public List<ProblemTest> getDisplayTestsByProblemId(int problemId) {
		List<ProblemTest> problemTests = problemsDao.getTestsByProblemId(problemId);
		List<ProblemTest> displayTests = new ArrayList<ProblemTest>();
		if(problemTests.size()>=2){
			displayTests.add(problemTests.get(0));
			displayTests.add(problemTests.get(1));
		}
		return displayTests;
	}



	@Override
	public void insertTestResult(ProblemTestResult testResult) {
		problemsDao.insertTestResult(testResult);
		
	}



	@Override
	public int insertSolution(ProblemSolution problemSolution) {
		return problemsDao.insertSolution(problemSolution);
		
	}
	
	@Override
	public void updateSolution(ProblemSolution problemSolution) {
		problemsDao.updateSolution(problemSolution);
		
	}

	@Override
	public List<ProblemSolution> getSolutions(ProblemSolution solution) {
		return problemsDao.getSolutions(solution);
	}
	
	@Override
	public ProblemSolution getSpecSolution(int solutionId){
		ProblemSolution paraSolution = new ProblemSolution();
		paraSolution.setSolutionId(solutionId);
		List<ProblemSolution> specSolutions = problemsDao.getSolutions(paraSolution);
		if(specSolutions != null)
			return specSolutions.get(0);
		else
			return null;
	}
	
	@Override
	public List<ProblemSolution> getMySolutions(int coderId){
		ProblemSolution paraSolution = new ProblemSolution();
		paraSolution.setSolutionCoderId(coderId);
		return problemsDao.getSolutions(paraSolution);
	}

	@Override
	public List<ProblemTestResult> getTestResultsBySolutionId(int solutionId){
		return problemsDao.getTestResultsBySolutionId(solutionId);
	}

}
