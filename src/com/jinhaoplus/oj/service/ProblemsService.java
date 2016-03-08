package com.jinhaoplus.oj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;

@Service
public interface ProblemsService {
	public List<Problem> getAllProblems();
	
	public Problem getProblemById(int id);
	
	public List<ProblemTest> getTestsByProblemId(int problemId);
	
	public List<ProblemTest> getDisplayTestsByProblemId(int problemId);
	
	public int insertSolution(ProblemSolution problemSolution);
	
	public void updateSolution(ProblemSolution problemSolution);
	
	public List<ProblemSolution> getAllSolutionsByCoderId(int coderId);
	
	public ProblemSolution getSolutionById(int solutionId);
	
	public void insertTestResult(ProblemTestResult testResult);
	
	
}
