package com.jinhaoplus.oj.service.langCore.impl;

import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.service.langCore.LangCoreService;

@Service
public class HaskellCoreServiceImpl implements LangCoreService {

	@Override
	public CommonMessage compileCode(int problemId,String path) {
		// TODO Auto-generated method stub
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
