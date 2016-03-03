package com.jinhaoplus.oj.service.langCore.impl;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
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
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.service.langCore.LangCoreService;

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
		String result="";
		return null;
	}

	@Override
	public String runCode(int problemId, String path) {
		List<ProblemTest> problemTests = problemsDao
				.getTestByProblemId(problemId);
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
				System.out.println("-------------------------------------------");
				System.out.println(runResultInfo.get());
				System.out.println(runErrorInfo.get());
				System.out.println("-------------------------------------------");
				runProcess.waitFor();
				runProcess.destroy();
				
				if(runProcess.exitValue()==0){
					System.out.println("run successfully");
				}else{
					System.out.println("run error");
					System.out.println(runErrorInfo.get());
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String OJResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createTempSourceFile(String fileOrDirName) {
		return fileOrDirName;
	}
	
	
}
