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
		return problemsDao.getList();
	}



	@Override
	public Problem getById(int id) {
		return problemsDao.getById(id);
	}



	@Override
	public List<ProblemTest> getTestByProblemId(int problemId) {
		return problemsDao.getTestByProblemId(problemId);
	}



	@Override
	public List<ProblemTest> getDisplayTestByProblemId(int problemId) {
		List<ProblemTest> problemTests = problemsDao.getTestByProblemId(problemId);
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
	public void insertSolution(ProblemSolution problemSolution) {
		problemsDao.insertSolution(problemSolution);
		
	}



	@Override
	public List<ProblemSolution> getAllSolutions(int coderId) {
		return problemsDao.getAllSolutions(coderId);
	}



	@Override
	public ProblemSolution getSolutionById(int solutionId) {
		return problemsDao.getSolutionById(solutionId);
	}

}
