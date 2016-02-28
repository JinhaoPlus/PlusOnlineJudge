package com.jinhaoplus.oj.service.langCore;

import com.jinhaoplus.oj.domain.ProblemSolution;

public interface CoreDispatcherService {
	public void dispatchCore(ProblemSolution solution); 
	public void workFlow(ProblemSolution solution,String path);
}
