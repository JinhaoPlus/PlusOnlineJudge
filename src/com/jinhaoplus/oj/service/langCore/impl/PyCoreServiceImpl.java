package com.jinhaoplus.oj.service.langCore.impl;

import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.ProblemSolution;
import com.jinhaoplus.oj.service.langCore.LangCoreService;

@Service
public class PyCoreServiceImpl implements LangCoreService {

	@Override
	public String compileCode(int problemId,String path) {
		// TODO Auto-generated method stub
		return null;
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
