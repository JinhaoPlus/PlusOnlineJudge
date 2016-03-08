package com.jinhaoplus.oj.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		return problemsDao.getAllProblems();
	}



	@Override
	public Problem getProblemById(int id) {
		return problemsDao.getProblemById(id);
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
	public List<ProblemSolution> getAllSolutionsByCoderId(int coderId) {
		return problemsDao.getAllSolutionsByCoderId(coderId);
	}



	@Override
	public ProblemSolution getSolutionById(int solutionId) {
		return problemsDao.getSolutionById(solutionId);
	}

}
