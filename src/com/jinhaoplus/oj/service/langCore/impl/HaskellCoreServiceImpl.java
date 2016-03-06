package com.jinhaoplus.oj.service.langCore.impl;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.dao.ProblemsDao;
import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.service.langCore.LangCoreService;

@Service
public class HaskellCoreServiceImpl implements LangCoreService {

	@Autowired
	private ProblemsDao problemsDao;

	public void setProblemsDao(ProblemsDao problemsDao) {
		this.problemsDao = problemsDao;
	}
	
	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors .newCachedThreadPool();

	
	@Override
	public CommonMessage insertSolution(ProblemSolution problemSolution) {
		problemsDao.insertSolution(problemSolution);
		return null;
	}
	
	@Override
	public CommonMessage compileCode(int problemId,String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProblemTestResult> runCode(int ProblemId, String path) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String OJResult(ProblemTest problemTest,ProblemTestResult testResult) {
		if(problemTest.getProblemTestOutput().equals(testResult.getResult())){
			return "AC";
		}else{
			return "WA";
		}
	}
	
	@Override
	public String createTempSourceFile(String fileOrDirName) {
		return fileOrDirName;
	}

}
