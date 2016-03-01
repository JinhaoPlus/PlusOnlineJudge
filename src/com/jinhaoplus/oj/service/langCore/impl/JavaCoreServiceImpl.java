package com.jinhaoplus.oj.service.langCore.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.dao.ProblemsDao;
import com.jinhaoplus.oj.domain.ProblemTest;
import com.jinhaoplus.oj.service.langCore.LangCoreService;

@Service
public class JavaCoreServiceImpl implements LangCoreService {

	@Autowired
	private ProblemsDao problemsDao;
	
	public void setProblemsDao(ProblemsDao problemsDao) {
		this.problemsDao = problemsDao;
	}


	@Override
	public String compileCode(int problemId, String path) {
		String result = "";
		try {
			String compileCommand = "javac " + path;
			Process compileProcess = Runtime.getRuntime().exec(compileCommand);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(compileProcess.getInputStream()));
			String compileResult = bufferedReader.readLine();
			result += "[compile INFO] " + compileResult + "\n";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public String runCode(int problemId, String path) {
		String result = "";
		List<ProblemTest> problemTests = problemsDao.getTestByProblemId(problemId);
		for (ProblemTest problemTest : problemTests) {
			try {
				String runCommand = "java " + path.substring(0,path.lastIndexOf("."));
				Process runProcess = Runtime.getRuntime().exec(runCommand);
				
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(runProcess.getOutputStream()));
				bufferedWriter.write(problemTest.getProblemTestInput());
				bufferedWriter.flush();
				runProcess.waitFor();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
				String runResult = bufferedReader.readLine();
				
				System.out.println(runResult);
				System.out.println(problemTest.getProblemTestOutput());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}


	@Override
	public String OJResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createTempSourceFile(String fileOrDirName) {
		try {
			String path = fileOrDirName.substring(0, fileOrDirName.lastIndexOf("."))+"/";
			File dir = new File(path);
			dir.mkdir();
			return path+"Test.java";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	

}
