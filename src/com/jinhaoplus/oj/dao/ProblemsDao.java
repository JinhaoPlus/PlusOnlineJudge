package com.jinhaoplus.oj.dao;

import java.util.List;

import com.jinhaoplus.oj.domain.Problem;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;

public interface ProblemsDao {
	public List<Problem> getAllProblems();

	public Problem getProblemById(int id);
	
	public void updateProblem(Problem problem);
	
	public List<ProblemTest> getTestsByProblemId(int problemId);
	
	public List<ProblemTest> getVisableTestsByProblemId(int problemId);
	
	public void insertTestResult(ProblemTestResult testResult);

	public int insertSolution(ProblemSolution problemSolution);
	
	public void updateSolution(ProblemSolution problemSolution);
	
	public List<ProblemSolution> getSolutions(ProblemSolution solution);
	
	public List<ProblemSolution> getSolutionsNoCode(ProblemSolution solution);

	public List<ProblemTestResult> getTestResultsBySolutionId(int solutionId);
}
