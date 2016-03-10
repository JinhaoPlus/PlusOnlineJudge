package com.jinhaoplus.oj.service.langCore.impl;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.common.ResultReadCallable;
import com.jinhaoplus.oj.common.TestWriteCallable;
import com.jinhaoplus.oj.dao.ProblemsDao;
import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.domain.ProblemTestResult;
import com.jinhaoplus.oj.service.langCore.LangCoreService;
import com.jinhaoplus.oj.util.PropertiesUtil;

@Service
public class PyCoreServiceImpl implements LangCoreService {

	@Autowired
	private ProblemsDao problemsDao;

	public void setProblemsDao(ProblemsDao problemsDao) {
		this.problemsDao = problemsDao;
	}
	
	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors .newCachedThreadPool();

	@Override
	//Interpreted Language doesn't need to be compiled : directly from source to be executable
	public CommonMessage compileCode(int problemId,String path) {
		CommonMessage message = new CommonMessage(PropertiesUtil.getProperty("COMPILE_SUCCESS_CODE"), 
				PropertiesUtil.getProperty("COMPILE_SUCCESS"), 
				"");
		return message;
	}

	@Override
	public List<ProblemTestResult> runCode(int problemId, int solutionId ,  String path) {
		List<ProblemTest> problemTests = problemsDao
				.getTestsByProblemId(problemId);
		List<ProblemTestResult> results = new ArrayList<ProblemTestResult>();
		for (ProblemTest problemTest : problemTests) {
			CommonMessage message = null;
			ProcessBuilder processBuilder;
			String fileName = path.substring(path.lastIndexOf("/")+1, path.length());
			String pyDir = path.substring(0,path.lastIndexOf("/"));
			processBuilder = new ProcessBuilder("python",fileName);
			processBuilder.directory(new File(pyDir));
			processBuilder.redirectErrorStream(true);

			try {
				Process runProcess = processBuilder.start();
				final InputStream inputStream = runProcess.getInputStream();
				final InputStream errorStream = runProcess.getErrorStream();
				final OutputStream outputStream = runProcess.getOutputStream();

				ResultReadCallable runResultThread = new ResultReadCallable(inputStream);
				ResultReadCallable runErrorThread = new ResultReadCallable(errorStream);
				TestWriteCallable runTestWriteThread = new TestWriteCallable(outputStream, problemTest.getProblemTestInput());
				Future<String> runErrorInfo = executor.submit(runErrorThread);
				Future<String> runResultInfo = executor.submit(runResultThread);
				executor.submit(runTestWriteThread);
				runProcess.waitFor();
				runProcess.destroy();
				
				if(runProcess.exitValue()==0){
					message = new CommonMessage(PropertiesUtil.getProperty("RUN_SUCCESS_CODE"), 
							PropertiesUtil.getProperty("RUN_SUCCESS"), 
							runResultInfo.get());
					ProblemTestResult testResult = new ProblemTestResult(problemId, problemTest.getProblemTestId(), runResultInfo.get(), "", message);
					String OJResult = this.OJResult(problemTest,testResult);
					testResult.setOjResult(OJResult);
					problemsDao.insertTestResult(testResult);
					testResult.setTestInput(problemTest.getProblemTestInput());
					testResult.setTestOutput(problemTest.getProblemTestOutput());
					results.add(testResult);
				}else{
					message = new CommonMessage(PropertiesUtil.getProperty("RUN_ERROR_CODE"), 
							PropertiesUtil.getProperty("RUN_ERROR"), 
							runErrorInfo.get());
					ProblemTestResult testResult = new ProblemTestResult(problemId, problemTest.getProblemTestId(), runResultInfo.get(), "", message);
					testResult.setSolutionId(solutionId);
					String OJResult = this.OJResult(problemTest,testResult);
					testResult.setOjResult(OJResult);
					problemsDao.insertTestResult(testResult);
					testResult.setTestInput(problemTest.getProblemTestInput());
					testResult.setTestOutput(problemTest.getProblemTestOutput());
					results.add(testResult);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return results;
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
