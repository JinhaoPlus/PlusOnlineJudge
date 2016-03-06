package com.jinhaoplus.oj.domain;

import java.util.Date;

public class ProblemSolution {
	private int solutionId;
	private int problemId;
	private String codeSubmit;
	private int solutionCoderId;
	private String solutionLanguage;
	private Date codeSubmitTime;
	private String finalOJResult;
	
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

	public int getSolutionCoderId() {
		return solutionCoderId;
	}


	public void setSolutionCoderId(int solutionCoderId) {
		this.solutionCoderId = solutionCoderId;
	}


	public Date getCodeSubmitTime() {
		return codeSubmitTime;
	}


	public void setCodeSubmitTime(Date codeSubmitTime) {
		this.codeSubmitTime = codeSubmitTime;
	}


	public String getSolutionLanguage() {
		return solutionLanguage;
	}


	public void setSolutionLanguage(String solutionLanguage) {
		this.solutionLanguage = solutionLanguage;
	}

	
	public String getFinalOJResult() {
		return finalOJResult;
	}


	public void setFinalOJResult(String finalOJResult) {
		this.finalOJResult = finalOJResult;
	}


	@Override
	public String toString() {
		return "ProblemSolution [problemId=" + problemId + ", codeSubmit=" + codeSubmit + "]";
	}
	
}
