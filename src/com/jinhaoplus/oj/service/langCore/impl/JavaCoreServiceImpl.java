package com.jinhaoplus.oj.service.langCore.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.dao.ProblemsDao;
import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.service.langCore.LangCoreService;

class ResultReadCallable implements Callable<String>{
	private InputStream inputStream;
	public ResultReadCallable(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	@Override
	public String call() throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer stringBuffer = new StringBuffer();
		try {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (line != null) {
					stringBuffer.append(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return stringBuffer.toString();
	}
}

class TestWriteCallable implements Callable<Object>{
	private OutputStream outputStream;
	private String testToWrite;
	public TestWriteCallable(OutputStream outputStream,String testToWrite) {
		this.outputStream = outputStream;
		this.testToWrite = testToWrite;
	}
	@Override
	public Object call() throws Exception {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		try {
			bufferedWriter.write(testToWrite);
			bufferedWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

@Service
public class JavaCoreServiceImpl implements LangCoreService {

	@Autowired
	private ProblemsDao problemsDao;

	public void setProblemsDao(ProblemsDao problemsDao) {
		this.problemsDao = problemsDao;
	}
	
	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors .newCachedThreadPool();

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
			final OutputStream outputStream = compileProcess.getOutputStream();

			ResultReadCallable compileResultThread = new ResultReadCallable(inputStream);
			ResultReadCallable compileErrorThread = new ResultReadCallable(errorStream);
			Future<String> compileErrorInfo = executor.submit(compileErrorThread);
			Future<String> compileResultInfo = executor.submit(compileResultThread);
			System.out.println("-------------------------------------------");
			System.out.println(compileResultInfo.get());
			System.out.println(compileErrorInfo.get());
			System.out.println("-------------------------------------------");
			compileProcess.waitFor();
			compileProcess.destroy();
			
			if(compileProcess.exitValue()==0){
				System.out.println("compile successfully");
			}else{
				System.out.println("compile error");
				System.out.println(compileErrorInfo.get());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String runCode(int problemId, String path) {
		List<ProblemTest> problemTests = problemsDao
				.getTestByProblemId(problemId);
		for (ProblemTest problemTest : problemTests) {
			CommonMessage message = null;
			ProcessBuilder processBuilder;
			String javaDir = path.substring(0,path.lastIndexOf("/"));
			processBuilder = new ProcessBuilder("cd",javaDir,"&&","java","Test");
			System.out.println("run Command:"+"cd "+javaDir+" && "+"java Test");
			processBuilder.redirectErrorStream(true);

			try {
				Process compileProcess = processBuilder.start();
				final InputStream inputStream = compileProcess.getInputStream();
				final InputStream errorStream = compileProcess.getErrorStream();
				final OutputStream outputStream = compileProcess.getOutputStream();

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
				compileProcess.waitFor();
				compileProcess.destroy();
				
				if(compileProcess.exitValue()==0){
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
