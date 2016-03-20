package com.jinhaoplus.oj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;

@Service
public interface ProblemsService {
	public List<Problem> getAllProblems(int coderId);
	
	public Problem getProblemById(int id);
	
	public void updateProblem(Problem problem, ProblemSolution solution);
	
	public List<ProblemTest> getTestsByProblemId(int problemId);
	
	public List<ProblemTest> getVisableTestsByProblemId(int problemId);
	
	public int insertSolution(ProblemSolution problemSolution);
	
	public void updateSolution(ProblemSolution problemSolution);
	
	public List<ProblemSolution> getSolutions(ProblemSolution solution);
	
	public ProblemSolution getSpecSolution(int solutionId);
	
	public List<ProblemSolution> getMySolutions(int coderId);

	public void insertTestResult(ProblemTestResult testResult);
	
	public List<ProblemTestResult> getTestResultsBySolutionId(int solutionId);
	
	public void visableTestResults(List<ProblemTestResult> testResults);
	
}
