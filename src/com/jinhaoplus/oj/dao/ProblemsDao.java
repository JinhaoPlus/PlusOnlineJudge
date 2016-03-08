package com.jinhaoplus.oj.dao;

import java.util.List;

import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;

public interface ProblemsDao {
	public List<Problem> getAllProblems();

	public Problem getProblemById(int id);
	
	public List<ProblemTest> getTestsByProblemId(int problemId);
	
	public void insertTestResult(ProblemTestResult testResult);
	
	public List<ProblemTestResult> getTestResultById(int solutionId);

	public int insertSolution(ProblemSolution problemSolution);
	
	public void updateSolution(ProblemSolution problemSolution);
	
	public List<ProblemSolution> getAllSolutionsByCoderId(int coderId);
	
	public ProblemSolution getSolutionById(int solutionId);
	
	public List<ProblemSolution> getTestResultsBySolutionId(int solutionId);
}
