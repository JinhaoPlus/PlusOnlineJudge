package com.jinhaoplus.oj.service.langCore;

public interface LangCoreService {
	//Compile source Code
	public String compileCode(int ProblemId,String path);
	//Run compiled Code
	public String runCode(Long codeId);
	//OJ Result of the Running
	public String OJResult();
}
