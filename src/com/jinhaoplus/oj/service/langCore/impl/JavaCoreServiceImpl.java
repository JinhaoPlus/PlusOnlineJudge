package com.jinhaoplus.oj.service.langCore.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.service.langCore.LangCoreService;

@Service
public class JavaCoreServiceImpl implements LangCoreService {

	@Override
	public String compileCode(int problemId, String path) {
		String result = "";
		try {
			String compileCommand = "javac " + path;
			System.out.println("ooo" + compileCommand);
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
	public String runCode(Long codeId) {
		// TODO Auto-generated method stub
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
