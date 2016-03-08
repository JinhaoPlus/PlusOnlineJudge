package com.jinhaoplus.oj.service;

import java.util.List;

import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTestResult;

public interface CoreDispatcherService {
	public void dispatchSolution(ProblemSolution solution); 
	public List<ProblemTestResult> workFlow(ProblemSolution solution,String path);
}
