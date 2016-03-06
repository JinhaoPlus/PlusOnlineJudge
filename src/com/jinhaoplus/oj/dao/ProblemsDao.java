package com.jinhaoplus.oj.dao;

import java.util.List;

import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;

public interface ProblemsDao {
	public List<Problem> getList();

	public Problem getById(int id);
	
	public List<ProblemTest> getTestByProblemId(int problemId);
	
	public void insertTestResult(ProblemTestResult testResult);
	
	public List<ProblemTestResult> getTestResultById(int solutionId);

	public void insertSolution(ProblemSolution problemSolution);
	
	public List<ProblemSolution> getAllSolutions(int coderId);
	
	public ProblemSolution getSolutionById(int solutionId);
}
