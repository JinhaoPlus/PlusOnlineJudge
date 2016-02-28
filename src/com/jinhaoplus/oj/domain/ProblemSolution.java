package com.jinhaoplus.oj.domain;

public class ProblemSolution {
	private int solutionId;
	private int problemId;
	private String codeSubmit;
	private String solutionCoder;
	private String solutionLanguage;
	
	
	public int getSolutionId() {
		return solutionId;
	}


	public void setSolutionId(int solutionId) {
		this.solutionId = solutionId;
	}


	public int getProblemId() {
		return problemId;
	}


	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}


	public String getCodeSubmit() {
		return codeSubmit;
	}


	public void setCodeSubmit(String codeSubmit) {
		this.codeSubmit = codeSubmit;
	}


	public String getSolutionCoder() {
		return solutionCoder;
	}


	public void setSolutionCoder(String solutionCoder) {
		this.solutionCoder = solutionCoder;
	}


	public String getSolutionLanguage() {
		return solutionLanguage;
	}


	public void setSolutionLanguage(String solutionLanguage) {
		this.solutionLanguage = solutionLanguage;
	}


	@Override
	public String toString() {
		return "ProblemSolution [problemId=" + problemId + ", codeSubmit=" + codeSubmit + "]";
	}
	
}
