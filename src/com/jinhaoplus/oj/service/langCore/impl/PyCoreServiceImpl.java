package com.jinhaoplus.oj.service.langCore.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.service.langCore.LangCoreService;

@Service
public class PyCoreServiceImpl implements LangCoreService {

	@Override
	//Interpreted Language doesn't need to be compiled : directly from source to be executable
	public String compileCode(int problemId,String path) {
		String result="";
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
		return fileOrDirName;
	}
	
	
}
