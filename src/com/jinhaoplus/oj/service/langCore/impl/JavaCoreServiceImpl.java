package com.jinhaoplus.oj.service.langCore.impl;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;


@Service
public class JavaCoreServiceImpl implements LangCoreService {

	@Autowired
	private ProblemsDao problemsDao;

	public void setProblemsDao(ProblemsDao problemsDao) {
		this.problemsDao = problemsDao;
	}
	
	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors .newCachedThreadPool();

	@Override
	public CommonMessage insertSolution(ProblemSolution problemSolution) {
		CommonMessage message = null;
		problemsDao.insertSolution(problemSolution);
		return null;
	}

	
	@Override
	public CommonMessage compileCode(int problemId, String path) {
		CommonMessage message = null;
		ProcessBuilder processBuilder;

		processBuilder = new ProcessBuilder("javac", path);
		processBuilder.redirectErrorStream(true);

		try {
			Process compileProcess = processBuilder.start();
			final InputStream inputStream = compileProcess.getInputStream();
			final InputStream errorStream = compileProcess.getErrorStream();

			ResultReadCallable compileResultThread = new ResultReadCallable(inputStream);
			ResultReadCallable compileErrorThread = new ResultReadCallable(errorStream);
			Future<String> compileErrorInfo = executor.submit(compileErrorThread);
			Future<String> compileResultInfo = executor.submit(compileResultThread);
			compileProcess.waitFor();
			compileProcess.destroy();
			
			if(compileProcess.exitValue()==0){
				message = new CommonMessage(PropertiesUtil.getProperty("COMPILE_SUCCESS_CODE"), 
						PropertiesUtil.getProperty("COMPILE_SUCCESS"), 
						compileResultInfo.get());
			}else{
				message = new CommonMessage(PropertiesUtil.getProperty("COMPILE_ERROR_CODE"), 
						PropertiesUtil.getProperty("COMPILE_ERROR"), 
						compileErrorInfo.get());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<ProblemTestResult> runCode(int problemId, String path) {
		List<ProblemTest> problemTests = problemsDao
				.getTestByProblemId(problemId);
		List<ProblemTestResult> results = new ArrayList<>();
		for (ProblemTest problemTest : problemTests) {
			CommonMessage message = null;
			ProcessBuilder processBuilder;
			String javaDir = path.substring(0,path.lastIndexOf("/"));
			processBuilder = new ProcessBuilder("java","Test");
			processBuilder.directory(new File(javaDir));
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
		try {
			String path = fileOrDirName.substring(0,
					fileOrDirName.lastIndexOf("."))
					+ "/";
			File dir = new File(path);
			dir.mkdir();
			return path + "Test.java";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
