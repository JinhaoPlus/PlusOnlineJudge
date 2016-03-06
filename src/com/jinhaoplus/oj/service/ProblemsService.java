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
	public Problem getById(int id);
	public List<ProblemTest> getTestByProblemId(int problemId);
	public List<ProblemTest> getDisplayTestByProblemId(int problemId);
	public void insertTestResult(ProblemTestResult testResult);
	public void insertSolution(ProblemSolution problemSolution);
	public List<ProblemSolution> getAllSolutions(int coderId);
	public ProblemSolution getSolutionById(int solutionId);
}
