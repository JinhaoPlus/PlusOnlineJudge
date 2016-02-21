package com.jinhaoplus.oj.service;

public interface PyCoreService {
	//Compile python source Code
	public String compileCode(Long codeId);
	//Run the compiled Code
	public String runCode(Long codeId);
	//OJ Result of the Running
	public String OJResult();
}
