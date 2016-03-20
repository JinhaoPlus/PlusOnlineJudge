package com.jinhaoplus.oj.util;

import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;

public class DisplayRunUtils {
	private DisplayRunUtils(){
		
	}
	
	public static ProblemSolution sourceForACE(ProblemSolution solution) {
		String source = solution.getCodeSubmit();
		String modifySource = source.replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r").replaceAll("\"", "\\\\\"");
		solution.setCodeSubmit(modifySource);
		return solution;
	}
	
	public static ProblemTest displayTests(ProblemTest problemTest){
		problemTest.setProblemTestInput(problemTest.getProblemTestInput().replaceAll("\\n", "↵<br/>"));
		problemTest.setProblemTestOutput(problemTest.getProblemTestOutput().replaceAll("\\n", "↵<br/>"));
		return problemTest;
	}
	
	public static ProblemTestResult displayResults(ProblemTestResult testResult){
		testResult.setTestInput(testResult.getTestInput().replaceAll("\\n", "↵<br/>"));
		testResult.setTestOutput(testResult.getTestOutput().replaceAll("\\n", "↵<br/>"));
		testResult.setResult(testResult.getResult().replaceAll("\\n", "↵<br/>"));
		return testResult;
	}
}
