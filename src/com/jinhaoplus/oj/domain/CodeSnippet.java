package com.jinhaoplus.oj.domain;

import java.util.Arrays;
import java.util.Date;

public class CodeSnippet {
	private int codeSnippetsId;
	private String codeSnippit;
	private String snippetDescription;
	private String[] snippetTags;
	private Date snippetSavedDate;
	public Date getSnippetSavedDate() {
		return snippetSavedDate;
	}
	public void setSnippetSavedDate(Date snippetSavedDate) {
		this.snippetSavedDate = snippetSavedDate;
	}
	public int getCodeSnippetsId() {
		return codeSnippetsId;
	}
	public void setCodeSnippetsId(int codeSnippetsId) {
		this.codeSnippetsId = codeSnippetsId;
	}
	public String getCodeSnippit() {
		return codeSnippit;
	}
	public void setCodeSnippit(String codeSnippit) {
		this.codeSnippit = codeSnippit;
	}
	public String getSnippetDescription() {
		return snippetDescription;
	}
	public void setSnippetDescription(String snippetDescription) {
		this.snippetDescription = snippetDescription;
	}
	public String[] getSnippetTags() {
		return snippetTags;
	}
	public void setSnippetTags(String[] snippetTags) {
		this.snippetTags = snippetTags;
	}
	@Override
	public String toString() {
		return "CodeSnippet [codeSnippetsId=" + codeSnippetsId + ", codeSnippit=" + codeSnippit
				+ ", snippetDescription=" + snippetDescription + ", snippetTags=" + Arrays.toString(snippetTags)
				+ ", snippetSavedDate=" + snippetSavedDate + "]";
	}
	
}
