package com.jinhaoplus.oj.service;

public interface PyCoreService {
	//对python源程序的编译
	public String compileCode(Long codeId);
	//对python程序的运行
	public String runCode(Long codeId);
	//对python程序运行的Online Judge结果的分析
	public String OJResult();
}
