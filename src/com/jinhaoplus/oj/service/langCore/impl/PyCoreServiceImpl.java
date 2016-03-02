package com.jinhaoplus.oj.service.langCore.impl;

import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.service.langCore.LangCoreService;

@Service
public class PyCoreServiceImpl implements LangCoreService {

	@Override
	//Interpreted Language doesn't need to be compiled : directly from source to be executable
	public CommonMessage compileCode(int problemId,String path) {
		String result="";
		return null;
	}

	@Override
	public String runCode(int ProblemId, String path) {
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
