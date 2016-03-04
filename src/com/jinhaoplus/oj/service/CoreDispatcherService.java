package com.jinhaoplus.oj.service;

import com.jinhaoplus.oj.domain.ProblemSolution;

public interface CoreDispatcherService {
	public void dispatchCore(ProblemSolution solution); 
	public void workFlow(ProblemSolution solution,String path);
}
