package com.jinhaoplus.oj.service;

public interface CCoreService {
	//Compile C source Code
	public String compileCode(Long codeId);
	//Run the compiled Code
	public String runCode(Long codeId);
	//OJ Result of the Running
	public String OJResult();
}
