package com.jinhaoplus.oj.domain;

public class Problem{
	private Integer problemId;
	private String problemDigest;
	private String problemCategory;
	private String problemContent;
	private String problemLanguage;
	
	public Integer getProblemId() {
		return problemId;
	}
	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	public String getProblemDigest() {
		return problemDigest;
	}
	public void setProblemDigest(String problemDigest) {
		this.problemDigest = problemDigest;
	}
	public String getProblemCategory() {
		return problemCategory;
	}
	public void setProblemCategory(String problemCategory) {
		this.problemCategory = problemCategory;
	}
	public String getProblemContent() {
		return problemContent;
	}
	public void setProblemContent(String problemContent) {
		this.problemContent = problemContent;
	}
	public String getProblemLanguage() {
		return problemLanguage;
	}
	public void setProblemLanguage(String problemLanguage) {
		this.problemLanguage = problemLanguage;
	}
	@Override
	public String toString() {
		return "Problem [problemId=" + problemId + ", problemDigest=" + problemDigest + ", problemCategory="
				+ problemCategory + ", problemContent=" + problemContent + ", problemLanguage=" + problemLanguage + "]";
	}
	
}
