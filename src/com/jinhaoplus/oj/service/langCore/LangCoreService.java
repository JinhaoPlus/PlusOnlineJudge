package com.jinhaoplus.oj.service.langCore;

import java.util.List;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;

public interface LangCoreService {
	//persist source to file to store
	public String createTempSourceFile(String fileOrDirName);
	//Store the source to database
	public CommonMessage insertSolution(ProblemSolution problemSolution);
	//Compile source Code
	public CommonMessage compileCode(int ProblemId,String path);
	//Run compiled Code
	public List<ProblemTestResult> runCode(int problemId,String path);
	//OJ Result of the Running
	public String OJResult(ProblemTest problemTest,ProblemTestResult testResult);
}
