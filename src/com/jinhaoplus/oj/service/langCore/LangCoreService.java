package com.jinhaoplus.oj.service.langCore;

import com.jinhaoplus.oj.domain.CommonMessage;

public interface LangCoreService {
	public String createTempSourceFile(String fileOrDirName);
	//Compile source Code
	public CommonMessage compileCode(int ProblemId,String path);
	//Run compiled Code
	public String runCode(int problemId,String path);
	//OJ Result of the Running
	public String OJResult();
}
